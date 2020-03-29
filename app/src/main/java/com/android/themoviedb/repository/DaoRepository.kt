package com.android.themoviedb.repository

import androidx.lifecycle.LiveData
import com.android.themoviedb.local.RepositoryDao
import com.android.themoviedb.model.MovieDetailDatabase
import com.android.themoviedb.model.MovieDetailMapper
import com.android.themoviedb.model.MovieDetailResult

class DaoRepository(
    private val repositoryDao: RepositoryDao,
    private val mapper: MovieDetailMapper
) {

    fun storeResponseData(data: MovieDetailResult) {
        repositoryDao.saveFavorite(mapper.transform(data))
    }

    fun getAllData(): LiveData<List<MovieDetailDatabase>> =
        repositoryDao.getAllFavorite()
}