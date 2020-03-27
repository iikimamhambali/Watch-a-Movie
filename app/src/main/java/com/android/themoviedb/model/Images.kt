package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
    @SerializedName("base_url") val baseUrl: String,
    @SerializedName("secure_base_url") val secureBaseUrl: String,
    @SerializedName("backdrop_sizes") val backdropSizes: MutableList<String>,
    @SerializedName("logo_sizes") val logoSizes: MutableList<String>,
    @SerializedName("poster_sizes") val posterSizes: MutableList<String>,
    @SerializedName("profile_sizes") val profileSizes: MutableList<String>,
    @SerializedName("still_sizes") val stillSizes: MutableList<String>
) : Parcelable