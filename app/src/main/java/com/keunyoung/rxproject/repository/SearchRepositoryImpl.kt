package com.keunyoung.rxproject.repository

import android.util.Log
import com.keunyoung.rxproject.SearchService
import com.keunyoung.rxproject.model.ListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchRepositoryImpl(private val searchService: SearchService) : SearchRepository {
	override fun search(query: String): Observable<List<ListItem>> {
		return searchService.searchImage(query)
			.zipWith(searchService.searchVideo(query)) { imageResult, videoResult ->
				mutableListOf<ListItem>().apply {
					addAll(imageResult.documents)
					addAll(videoResult.documents)
				}.sortedBy { it.dateTime }
			}
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
	}
}