package com.evaluation.photoandvideoapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.evaluation.photoandvideoapp.network.APIClient
import com.evaluation.photoandvideoapp.network.APIEndPoint
import com.evaluation.photoandvideoapp.response.PhotoSearchResponse
import com.evaluation.photoandvideoapp.response.VideoSearchResponse
import com.evaluation.photoandvideoapp.utils.Constants
import com.evaluation.photoandvideoapp.utils.getJsonFromAssets
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel {
	
	var apiEndPoint : APIEndPoint? = null
	var videoSearchLive = MutableLiveData<VideoSearchResponse>()
	var photoSearchResponseLive = MutableLiveData<PhotoSearchResponse>()
	var curatedResponse = MutableLiveData<PhotoSearchResponse>()
	
	fun getPhotoList() {
		apiEndPoint = APIClient.getClient()!!.create(APIEndPoint::class.java);
		
		var response = apiEndPoint!!.getPhotoSearchResponse(Constants.APIKEY,"animal", 20);
		response.enqueue(object : Callback<PhotoSearchResponse> {
			override fun onFailure(call : Call<PhotoSearchResponse>, t : Throwable) {
			
			}
			
			override fun onResponse(call : Call<PhotoSearchResponse>, response : Response<PhotoSearchResponse>) {
				photoSearchResponseLive.value = response.body()
			}
		});
	}
	
	fun getVideoList() {
		apiEndPoint = APIClient.getClient()!!.create(APIEndPoint::class.java);
		
		var response = apiEndPoint!!.getVideos(Constants.APIKEY,"animal", 20);
		response.enqueue(object : Callback<VideoSearchResponse> {
			override fun onFailure(call : Call<VideoSearchResponse>, t : Throwable) {
			
			}
			
			override fun onResponse(call : Call<VideoSearchResponse>, response : Response<VideoSearchResponse>) {
				videoSearchLive.value = response.body()
			}
		});
	}
	
	
	fun getCurated() {
		apiEndPoint = APIClient.getClient()!!.create(APIEndPoint::class.java);
		
		var response = apiEndPoint!!.getCuratedResponse(Constants.APIKEY);
		response.enqueue(object : Callback<PhotoSearchResponse> {
			override fun onFailure(call : Call<PhotoSearchResponse>, t : Throwable) {
			
			}
			
			override fun onResponse(call : Call<PhotoSearchResponse>, response : Response<PhotoSearchResponse>) {
				curatedResponse.value = response.body()
			}
		});
	}
	
	fun getVideoLiveData() :MutableLiveData<VideoSearchResponse>{
		return videoSearchLive
	}
	
	fun getPhotoReponses() : MutableLiveData<PhotoSearchResponse>{
		return  photoSearchResponseLive
	}
	
	fun getCuratedResponses() : MutableLiveData<PhotoSearchResponse>  {
		return curatedResponse
	}
	
	fun getCurateResponse(context : Context) : PhotoSearchResponse{
		val gson = Gson()
		return  gson.fromJson(getJsonFromAssets(context,Constants.curated),PhotoSearchResponse::class.java)
	}
	
	fun getPhotoResponse(context : Context) : PhotoSearchResponse{
		
		
		val gson = Gson()
		return  gson.fromJson(getJsonFromAssets(context ,Constants.data),PhotoSearchResponse::class.java)
	}
	
	fun getVideoResponse(context : Context) : VideoSearchResponse{
		val gson = Gson()
		return  gson.fromJson(getJsonFromAssets(context ,Constants.video),VideoSearchResponse::class.java)
	}
}