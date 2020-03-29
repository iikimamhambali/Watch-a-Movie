package com.android.themoviedb.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.themoviedb.model.MovieDetailDatabase

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM table_detail")
    fun getAllFavorite(): LiveData<List<MovieDetailDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavorite(movieDetail: MovieDetailDatabase)

    @Query("DELETE FROM table_detail")
    fun deleteAll()
}