package com.linguity.app.api.responses

import com.google.gson.annotations.SerializedName

data class ResponseEnglishLearning(

    @field:SerializedName("msg")
    val msg: String? = null,

    @field:SerializedName("row")
    val row: List<Article>? = null,

    @field:SerializedName("status")
    val status: String? = null
)
