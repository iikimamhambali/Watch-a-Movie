package com.android.themoviedb.local

import android.content.Context
import androidx.room.Room

fun makeLocalDatabase(
    context: Context
): LocalDatabase {
    return Room.databaseBuilder(context, LocalDatabase::class.java, "movie.db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}

fun makeRepositoryDao(localDatabase: LocalDatabase): RepositoryDao {
    return localDatabase.repositoryDao
}