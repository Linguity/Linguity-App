package com.linguity.app.api.responses

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

    @field:SerializedName("msg")
    val msg: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("tokenId")
    val tokenId: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)
