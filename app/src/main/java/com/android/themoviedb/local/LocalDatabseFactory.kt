package com.android.themoviedb.local

import android.content.Context
import androidx.room.Room

object LocalDatabseFactory {

    fun makeLocalDatabase(context: Context): LocalDatabase {
        return Room.databaseBuilder(context, LocalDatabase::class.java, "tga.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun makeRepositoryDao(localDatabase: LocalDatabase): RepositoryDao {
        return localDatabase.repositoryDao
    }
}