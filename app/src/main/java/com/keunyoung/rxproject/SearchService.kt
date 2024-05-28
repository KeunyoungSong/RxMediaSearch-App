package com.keunyoung.rxproject

import com.keunyoung.rxproject.model.ImageListResponse
import com.keunyoung.rxproject.model.VideoListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {
	
	@Headers("Authorization: KakaoAK c2f850eb276516bb881564663fd727eb")
	@GET("image")
	fun searchImage(@Query("query") query: String): Observable<ImageListResponse>
	
	@Headers("Authorization: KakaoAK c2f850eb276516bb881564663fd727eb")
	@GET("vclip")
	fun searchVideo(@Query("query") query: String): Observable<VideoListResponse>
	
}