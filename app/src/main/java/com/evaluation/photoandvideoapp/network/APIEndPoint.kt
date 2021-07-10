package com.evaluation.photoandvideoapp.network

import com.evaluation.photoandvideoapp.response.PhotoSearchResponse
import com.evaluation.photoandvideoapp.response.Photos
import com.evaluation.photoandvideoapp.response.Video
import com.evaluation.photoandvideoapp.response.VideoSearchResponse
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface APIEndPoint {
	
	@GET(PHOTOSEARCH)
	fun getPhotoSearchResponse(
			@Header("Authorization") apikey: String,
			@Query("query") query : String,
			@Query("per_page") per_page : Int
	): Call<PhotoSearchResponse>
	
	@GET(CURATED)
	fun getCuratedResponse(
			@Header("Authorization") apikey: String
	): Call<PhotoSearchResponse>
	
	
	@GET(VIDEOS)
	fun getVideos(
			@Header("Authorization") apikey: String,
			@Query("query") query : String,
			@Query("per_page") per_page : Int
	): Call<VideoSearchResponse>
	
	
	@GET(PHOTOOBJECT)
	fun getPhotoObject(
			@Header("Authorization") apikey: String,
			@Path("id") id : Int
	): Call<Photos>
	
	@GET(VIDEOOBJECT)
	fun getVideoObject(
			@Header("Authorization") apikey: String,
			@Path("id") id : Int
	): Call<Video>
	
	
	companion object {
		const val PHOTOSEARCH = "/v1/search"
		const val CURATED = "/v1/curated"
		const val VIDEOS = "/videos/search"
		const val PHOTOOBJECT = "/v1/photos/{id}"
		const val VIDEOOBJECT = "/videos/videos/{id}"
		
	}
}