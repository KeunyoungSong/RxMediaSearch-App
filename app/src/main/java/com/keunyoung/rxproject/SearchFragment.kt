package com.keunyoung.rxproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.keunyoung.rxproject.databinding.FragmentSearchBinding
import com.keunyoung.rxproject.list.ItemHandler
import com.keunyoung.rxproject.list.ListAdapter
import com.keunyoung.rxproject.model.ListItem
import com.keunyoung.rxproject.repository.SearchRepository
import com.keunyoung.rxproject.repository.SearchRepositoryImpl

class SearchFragment : Fragment() {
	private var _binding: FragmentSearchBinding? = null
	private val binding get() = _binding!!
	private val viewModel: SearchViewModel by viewModels {
		SearchViewModel.SearchViewModelFactory(SearchRepositoryImpl(RetrofitManager.searchService))
	}
	private val adapter by lazy { ListAdapter() }
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		return FragmentSearchBinding.inflate(inflater, container, false).apply {
			_binding = this
			lifecycleOwner = viewLifecycleOwner
			viewModel = this@SearchFragment.viewModel
		}.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initView()
		initViewModel()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	
	private fun initView() {
		binding.apply {
			recyclerView.adapter = adapter
		}
	}
	
	private fun initViewModel() {
		viewModel.listLiveData.observe(viewLifecycleOwner) { listItems ->
			with(binding) {
				if (listItems.isEmpty()) {
					emptyTextView.isVisible = true
					recyclerView.isVisible = false
				} else {
					emptyTextView.isVisible = false
					recyclerView.isVisible = true
				}
				adapter.submitList(listItems)
			}
		}
	}
	
	fun searchKeyword(text: String) {
		viewModel.search(text)
	}
	
	class Handler(private val viewModel: SearchViewModel) :ItemHandler {
		override fun onClickFavorite(item: ListItem) {
			viewModel.toggleFavorite(item)
		}
	}
	
}