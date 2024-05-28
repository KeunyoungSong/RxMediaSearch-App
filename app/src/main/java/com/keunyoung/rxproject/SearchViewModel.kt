package com.keunyoung.rxproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keunyoung.rxproject.model.ListItem
import com.keunyoung.rxproject.repository.SearchRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SearchViewModel(
	private val searchRepository: SearchRepository
) : ViewModel() {
	
	private val _listLiveData = MutableLiveData<List<ListItem>>()
	val listLiveData: LiveData<List<ListItem>> get() = _listLiveData
	
	private val _showLoading = MutableLiveData<Boolean>()
	val showLoading: LiveData<Boolean> get() = _showLoading
	
	private var disposable: CompositeDisposable? = CompositeDisposable()
	
	override fun onCleared() {
		super.onCleared()
		disposable?.dispose()
		disposable = null
	}
	
	fun search(query: String) {
		disposable?.add(
			searchRepository.search(query)
				.doOnSubscribe { _showLoading.value = true }
				.doOnTerminate { _showLoading.value = false }
				.subscribe({ list -> _listLiveData.value = list }, { _listLiveData.value = emptyList() })
		)
	}
	
	class SearchViewModelFactory(private val searchRepository: SearchRepository) : ViewModelProvider.Factory {
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			return SearchViewModel(searchRepository) as T
		}
	}
}
