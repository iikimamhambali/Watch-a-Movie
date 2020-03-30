package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieReviewList(
    @SerializedName("author") val author: String? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("url") val url: String? = null
) : Parcelable