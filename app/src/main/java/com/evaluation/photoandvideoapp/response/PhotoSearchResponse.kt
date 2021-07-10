package com.evaluation.photoandvideoapp.response

import com.google.gson.annotations.SerializedName

data class PhotoSearchResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("per_page")
    val per_page: Int? = null,

    @field:SerializedName("photos")
    val photos: List<Photos?>? = null,

    @field:SerializedName("total_results")
    val total_results: Int? = null,

    @field:SerializedName("next_page")
    val next_page: String? = null

)

data class Photos(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("height")
    val height: Int? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("photographer")
    val photographer: String? = null,

    @field:SerializedName("photographer_url")
    val photographer_url: String? = null,

    @field:SerializedName("photographer_id")
    val photographer_id: Int? = null,

    @field:SerializedName("avg_color")
    val avg_color: String? = null,

    @field:SerializedName("src")
    val src: Src? = null,

    @field:SerializedName("liked")
    val liked: Boolean? = null

)

data class Src(

    @field:SerializedName("original")
    val original: String? = null,

    @field:SerializedName("large2x")
    val large2x: String? = null,

    @field:SerializedName("large")
    val large: String? = null,

    @field:SerializedName("medium")
    val medium: String? = null,

    @field:SerializedName("small")
    val small: String? = null,

    @field:SerializedName("portrait")
    val portrait: String? = null,

    @field:SerializedName("landscape")
    val landscape: String? = null,

    @field:SerializedName("tiny")
    val tiny: String? = null
)