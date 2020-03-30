package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResult(@SerializedName("results") val results : MutableList<MovieList>,
                       @SerializedName("page") var page : Int,
                       @SerializedName("total_results") val totalResults : Int,
                       @SerializedName("dates") val dates : Dates,
                       @SerializedName("total_pages") val totalPages : Int) : Parcelable