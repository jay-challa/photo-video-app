package com.evaluation.photoandvideoapp.response

import com.google.gson.annotations.SerializedName

data class VideoSearchResponse(
		@field:SerializedName("page")
        val page: Int,
		@field:SerializedName("per_page")
        val per_page: Int,
		@field:SerializedName("total_results")
	    val total_results: Int,
		@field:SerializedName("url")
        val url: String,
		@field:SerializedName("videos")
        val videos: List<Video>
)

data class VideoPicture(
		@field:SerializedName("id")
		val id: Int,
		@field:SerializedName("nr")
		val nr: Int,
		@field:SerializedName("picture")
		val picture: String
)

data class VideoFile(
		@field:SerializedName("file_type")
		val file_type: String,
		@field:SerializedName("height")
		val height: Int,
		@field:SerializedName("id")
		val id: Int,
		@field:SerializedName("link")
		val link: String,
		@field:SerializedName("quality")
		val quality: String,
		@field:SerializedName("width")
		val width: Int
)

data class Video(
		@field:SerializedName("avg_color")
		val avg_color: Any,
		@field:SerializedName("duration")
		val duration: Int,
		@field:SerializedName("full_res")
		val full_res: Any,
		@field:SerializedName("height")
		val height: Int,
		@field:SerializedName("id")
		val id: Int,
		@field:SerializedName("image")
		val image: String,
		@field:SerializedName("tags")
		val tags: List<Any>,
		@field:SerializedName("url")
		val url: String,
		@field:SerializedName("user")
		val user: User,
		@field:SerializedName("video_files")
		val video_files: List<VideoFile>,
		@field:SerializedName("video_pictures")
		val video_pictures: List<VideoPicture>,
		@field:SerializedName("width")
		val width: Int,
		@field:SerializedName("liked")
		val liked: Boolean


)

data class User(
		@field:SerializedName("id")
		val id: Int,
		@field:SerializedName("name")
		val name: String,
		@field:SerializedName("url")
		val url: String
)