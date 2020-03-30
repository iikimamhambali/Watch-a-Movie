package com.android.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.themoviedb.base.BaseViewModel
import com.android.themoviedb.helper.Resource
import com.android.themoviedb.model.MovieDetailAdapter
import com.android.themoviedb.model.MovieDetailDatabase
import com.android.themoviedb.model.MovieDetailResult
import com.android.themoviedb.repository.DaoRepository

class DaoViewModel(private val repository: DaoRepository) : BaseViewModel() {

    private var data: LiveData<List<MovieDetailAdapter>> = repository.getAllData()

    fun storeDataMovie(data: MovieDetailResult): Boolean {
        repository.storeResponseData(data)
        return true
    }

    fun getAllData(): LiveData<List<MovieDetailAdapter>> {
        return data
    }

    fun deleteItem(id: Int) {
        repository.deleteItem(id)
    }
}