package com.keunyoung.rxproject.repository

import com.keunyoung.rxproject.model.ListItem
import io.reactivex.rxjava3.core.Observable

interface SearchRepository {
	
	fun search(query: String): Observable<List<ListItem>>
}