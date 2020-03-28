package com.android.themoviedb.local.converter

import androidx.room.TypeConverter
import com.android.themoviedb.model.BelongsCollection
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class BelongsCollectionObjectConverter {

    @TypeConverter
    fun fromString(value: String): BelongsCollection? {
        val listType = object : TypeToken<BelongsCollection>() {}.type
        return Gson().fromJson<BelongsCollection>(value, listType)
    }

    @TypeConverter
    fun fromList(data: BelongsCollection?): String {
        val gson = Gson()
        return gson.toJson(data)
    }
}