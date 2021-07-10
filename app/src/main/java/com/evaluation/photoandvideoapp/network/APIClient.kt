package com.evaluation.photoandvideoapp.network

import android.annotation.SuppressLint
import android.util.Log
import android.util.Log.d
import com.evaluation.photoandvideoapp.BuildConfig
import okhttp3.*
import okio.Buffer
import okio.BufferedSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.logging.Logger


class APIClient {

	companion object {
		private var retrofit : Retrofit? = null
		private var base_url = "https://api.pexels.com";
		
		 fun getClient() : Retrofit? {
			val interceptor = LogJsonInterceptor()
			//interceptor.level = HttpLoggingInterceptor.Level.BODY
			val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
			retrofit = Retrofit.Builder()
					.baseUrl(base_url)
					.addConverterFactory(GsonConverterFactory.create())
					.client(client)
					.build()
			return retrofit
		}
		
	}
	
}

class LogJsonInterceptor : Interceptor {
	@SuppressLint("LongLogTag")
	override fun intercept(chain : Interceptor.Chain) : Response {
		val request : Request = chain.request()
		val response = chain.proceed(request)
		val rawJson = response.body()!!.string()
		Log.d(BuildConfig.APPLICATION_ID, String.format("raw JSON response is: %s", rawJson));
		return response.newBuilder()
				.body(ResponseBody.create(response.body()!!.contentType(), rawJson)).build()
	}
	
}