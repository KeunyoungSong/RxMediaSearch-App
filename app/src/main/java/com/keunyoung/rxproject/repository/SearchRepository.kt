package com.keunyoung.rxproject.repository

import com.keunyoung.rxproject.model.ListItem
import io.reactivex.rxjava3.core.Observable as Observable1

interface SearchRepository {
	
	fun search(query: String): Observable1<List<ListItem>>
}