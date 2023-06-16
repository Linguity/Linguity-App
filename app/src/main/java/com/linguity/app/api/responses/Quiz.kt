package com.linguity.app.api.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(

    @field:SerializedName("is_open")
    val isOpen: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("is_answered")
    val isAnswered: Int? = null
) : Parcelable
