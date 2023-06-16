package com.linguity.app.api.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("writerBy")
    val writerBy: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("content")
    val content: String? = null
) : Parcelable