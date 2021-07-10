package com.evaluation.photoandvideoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.evaluation.photoandvideoapp.network.APIClient
import com.evaluation.photoandvideoapp.network.APIEndPoint
import com.evaluation.photoandvideoapp.response.PhotoSearchResponse
import com.evaluation.photoandvideoapp.response.Photos
import com.evaluation.photoandvideoapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoViewModel {
	
	var apiEndPoint : APIEndPoint? = null
	var photoResponseLiveData = MutableLiveData<Photos>()
	
	fun getPhotoObect(id : Int) {
		apiEndPoint = APIClient.getClient()!!.create(APIEndPoint::class.java);
		
		var response = apiEndPoint!!.getPhotoObject(Constants.APIKEY,id);
		response.enqueue(object : Callback<Photos> {
			override fun onFailure(call : Call<Photos>, t : Throwable) {
			
			}
			
			override fun onResponse(call : Call<Photos>, response : Response<Photos>) {
				photoResponseLiveData.value = response.body()
			}
		});
	}
	
	fun getPhotoResponseLiveDatas(): MutableLiveData<Photos>{
		return photoResponseLiveData;
	}
}