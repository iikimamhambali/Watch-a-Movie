package com.android.themoviedb.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.themoviedb.local.converter.*
import com.android.themoviedb.model.MovieDetailDatabase
import com.android.themoviedb.model.MovieDetailResult

@Database(
    entities = [(MovieDetailResult::class)],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [(BelongsCollectionObjectConverter::class), (GenreListConverter::class),
        (ProductionCompanyListConverter::class), (ProductionCountryListConverter::class), (SpokenLanguageListConverter::class)]
)
abstract class LocalDatabase : RoomDatabase() {

    abstract val repositoryDao: RepositoryDao
}