package com.android.themoviedb.local.converter

import androidx.room.TypeConverter
import com.android.themoviedb.model.ProductionCountry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class ProductionCountryListConverter {

    @TypeConverter
    fun fromString(value: String): List<ProductionCountry>? {
        val listType = object : TypeToken<List<ProductionCountry>>() {}.type
        return Gson().fromJson<List<ProductionCountry>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ProductionCountry>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}