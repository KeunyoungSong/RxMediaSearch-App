package com.keunyoung.rxproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.keunyoung.rxproject.model.ImageItem
import com.keunyoung.rxproject.model.ListItem
import com.keunyoung.rxproject.model.VideoItem
import com.keunyoung.rxproject.repository.SearchRepository
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
	
	@Rule
	@JvmField
	val rule = InstantTaskExecutorRule()
	
	@Mock
	lateinit var searchRepository: SearchRepository
	
	@Mock
	lateinit var mockLoadingObserve: Observer<Boolean>
	
	@Mock
	lateinit var mockListObserve: Observer<List<ListItem>>
	
	private lateinit var viewModel: SearchViewModel
	
	@Before
	fun setUp() {
		viewModel = SearchViewModel(searchRepository)
		viewModel.showLoading.observeForever(mockLoadingObserve)
		viewModel.listLiveData.observeForever(mockListObserve)
	}
	
	@Test
	fun searchNotEmpty() {
		Mockito.`when`(searchRepository.search(Mockito.anyString())).thenReturn(Observable.just(mockList()))
		
		viewModel.search("query")
		Mockito.verify(mockLoadingObserve, Mockito.times(1)).onChanged(true)
		Mockito.verify(mockListObserve, Mockito.times(1)).onChanged(Mockito.anyList())
		assert(!viewModel.listLiveData.value.isNullOrEmpty())
	}
	
	@Test
	fun searchEmpty() {
		Mockito.`when`(searchRepository.search(Mockito.anyString())).thenReturn(Observable.just(emptyList()))
		
		viewModel.search("query")
		Mockito.verify(mockLoadingObserve, Mockito.times(1)).onChanged(true)
		Mockito.verify(mockListObserve, Mockito.times(1)).onChanged(Mockito.anyList())
		assert(viewModel.listLiveData.value.isNullOrEmpty())
	}
	
	private fun mockList() = listOf(
		ImageItem("thumbnailUrl", "collection", "siteName", "docUrl", Date(), false),
		VideoItem("videoThumbnailUrl", "title", 100, "author", Date(), true),
		ImageItem("thumbnailUrl2", "collection2", "siteName2", "docUrl2", Date(), true)
	)
	
}

