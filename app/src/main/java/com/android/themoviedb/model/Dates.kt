package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dates(@SerializedName("maximum") var maximum: String,
                 @SerializedName("minimum") var minimum: String) : Parcelable