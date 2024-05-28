package com.keunyoung.rxproject

import com.keunyoung.rxproject.model.ImageListResponse
import com.keunyoung.rxproject.model.VideoListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {
	
	@Headers("Authorization: KakoAK b2613969061fe6dfb5d6ac25dbdba9dd")
	@GET("image")
	fun searchImage(@Query("query") query: String): Observable<ImageListResponse>
	
	@Headers("Authorization: KakoAK b2613969061fe6dfb5d6ac25dbdba9dd")
	@GET("video")
	fun searchVideo(@Query("query") query: String): Observable<VideoListResponse>
	
}