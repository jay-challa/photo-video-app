package com.evaluation.photoandvideoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.evaluation.photoandvideoapp.network.APIClient
import com.evaluation.photoandvideoapp.network.APIEndPoint
import com.evaluation.photoandvideoapp.response.Photos
import com.evaluation.photoandvideoapp.response.Video
import com.evaluation.photoandvideoapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoViewModel {
	
	var apiEndPoint : APIEndPoint? = null
	var videoObjectLive = MutableLiveData<Video>()
	
	fun getVideoObject(id : Int) {
		apiEndPoint = APIClient.getClient()!!.create(APIEndPoint::class.java);
		
		var response = apiEndPoint!!.getVideoObject(Constants.APIKEY,id);
		response.enqueue(object : Callback<Video> {
			override fun onFailure(call : Call<Video>, t : Throwable) {
			
			}
			
			override fun onResponse(call : Call<Video>, response : Response<Video>) {
				videoObjectLive.value = response.body()
			}
		});
	}
	
	fun getVideoObjectLives(): MutableLiveData<Video>{
		return videoObjectLive;
	}
}
