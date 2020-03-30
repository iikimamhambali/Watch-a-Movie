package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieReviewResult(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("page") val page: Int = 0,
    @SerializedName("results") val results: MutableList<MovieReviewList> = mutableListOf(),
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("total_results") val totalResults: Int = 0
) : Parcelable