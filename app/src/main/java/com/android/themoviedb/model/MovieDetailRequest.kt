package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailRequest(
    @SerializedName("movie_id") val movieId: Int,
    @SerializedName("api_key") val apiKey: String,
    @SerializedName("language") val language: String,
    @SerializedName("append_to_response") val appendRoResponse: String
) : Parcelable