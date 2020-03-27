package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpokenLanguage(
    @SerializedName("iso_639_1") val iso: String,
    @SerializedName("name") val name: String
) : Parcelable