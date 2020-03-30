package com.android.themoviedb.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.themoviedb.model.MovieDetailAdapter
import com.android.themoviedb.model.MovieDetailDatabase

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM table_detail")
    fun getAllFavorite(): LiveData<List<MovieDetailAdapter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavorite(movieDetail: MovieDetailDatabase)

    @Query("DELETE FROM table_detail WHERE id = :id")
    fun delete(id: Int)
}