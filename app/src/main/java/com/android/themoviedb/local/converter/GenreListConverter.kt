package com.android.themoviedb.local.converter

import androidx.room.TypeConverter
import com.android.themoviedb.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class GenreListConverter {
    @TypeConverter
    fun fromString(value: String): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson<List<Genre>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Genre>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}