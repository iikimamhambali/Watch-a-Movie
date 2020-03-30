package com.android.themoviedb.local.converter

import androidx.room.TypeConverter
import com.android.themoviedb.model.ProductionCompany
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class ProductionCompanyListConverter {
    @TypeConverter
    fun fromString(value: String): List<ProductionCompany>? {
        val listType = object : TypeToken<List<ProductionCompany>>() {}.type
        return Gson().fromJson<List<ProductionCompany>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ProductionCompany>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}