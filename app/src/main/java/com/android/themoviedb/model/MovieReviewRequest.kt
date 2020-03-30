package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieReviewRequest(
    @SerializedName("movie_id") val movieId: Int,
    @SerializedName("api_key") val apiKey: String,
    @SerializedName("language") val language: String,
    @SerializedName("page") val page: Int
) : Parcelable