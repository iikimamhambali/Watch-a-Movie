package com.android.themoviedb.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConfirgurationResult(
    @SerializedName("images") val images: Images,
    @SerializedName("change_keys") val changeKeys: MutableList<String>
) : Parcelable