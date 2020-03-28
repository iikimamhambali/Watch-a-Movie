package com.android.themoviedb.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.themoviedb.model.MovieDetailDatabase

@Database(
    entities = [(MovieDetailDatabase::class)],
    version = 1,
    exportSchema = false
)

abstract class LocalDatabase : RoomDatabase() {

    abstract val repositoryDao: RepositoryDao
}
