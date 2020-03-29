package com.android.themoviedb.viewmodel

import com.android.themoviedb.base.BaseViewModel
import com.android.themoviedb.model.MovieDetailResult
import com.android.themoviedb.repository.DaoRepository

class DaoViewModel(private val repository: DaoRepository) : BaseViewModel() {

    fun storeDataMovie(data: MovieDetailResult): Boolean {
        repository.storeResponseData(data)
        return true
    }

    fun getAllData() {
        repository.getAllData()
    }

    fun setItem() {
        repository.setItems()
    }
}