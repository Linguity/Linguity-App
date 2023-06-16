package com.linguity.app.api.responses

import com.google.gson.annotations.SerializedName

data class ResponsePronunciationPredict(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("is_open")
	val isOpen: Boolean? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("is_answered")
	val isAnswered: Boolean? = null,

	@field:SerializedName("check")
	val check: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
