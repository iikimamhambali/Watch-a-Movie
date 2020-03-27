package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieRequest(
    @SerializedName("api_key") val apiKey: String,
    @SerializedName("language") val language: String,
    @SerializedName("page") val page: Int,
    @SerializedName("region") val region: String
) : Parcelable