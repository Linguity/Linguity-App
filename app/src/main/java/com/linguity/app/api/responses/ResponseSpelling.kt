package com.linguity.app.api.responses

import com.google.gson.annotations.SerializedName

data class ResponseSpelling(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("row")
	val row: List<Quiz>? = null,

	@field:SerializedName("status")
	val status: String? = null
)


