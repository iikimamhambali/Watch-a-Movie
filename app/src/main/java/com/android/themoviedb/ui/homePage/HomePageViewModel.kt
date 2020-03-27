package com.android.themoviedb.ui.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.themoviedb.base.BaseViewModel
import com.android.themoviedb.helper.Resource
import com.android.themoviedb.model.ConfirgurationResult
import com.android.themoviedb.model.MovieRequest
import com.android.themoviedb.model.MovieResult
import com.android.themoviedb.repository.MovieRepository

class HomePageViewModel(repository: MovieRepository) : BaseViewModel() {

    private val nowPlayingRequest = MutableLiveData<MovieRequest>()
    private val popularRequest = MutableLiveData<MovieRequest>()
    private val upComingRequest = MutableLiveData<MovieRequest>()
    private val topRatedRequest = MutableLiveData<MovieRequest>()
    private val configurationRequest = MutableLiveData<String>()

    val listNowPlayingMovie: LiveData<Resource<MovieResult>> = Transformations
        .switchMap(nowPlayingRequest) {
            repository.getNowPlaying(it)
        }

    fun getNowPlayingMovie(request: MovieRequest) {
        nowPlayingRequest.value = request
    }

    val listPopularMovie: LiveData<Resource<MovieResult>> = Transformations
        .switchMap(popularRequest) {
            repository.getPopular(it)
        }

    fun getPopularMovie(request: MovieRequest) {
        popularRequest.value = request
    }

    val listUpComingMovie: LiveData<Resource<MovieResult>> = Transformations
        .switchMap(upComingRequest) {
            repository.getUpComing(it)
        }

    fun getUpComingMovie(request: MovieRequest) {
        upComingRequest.value = request
    }

    val listTopRatedMovie: LiveData<Resource<MovieResult>> = Transformations
        .switchMap(topRatedRequest) {
            repository.getTopRated(it)
        }

    fun getTopRatedMovie(request: MovieRequest) {
        topRatedRequest.value = request
    }

    val configuration: LiveData<Resource<ConfirgurationResult>> = Transformations
        .switchMap(configurationRequest) {
            repository.getConfiguration(it)
        }

    fun getConfiguration(key: String) {
        configurationRequest.value = key
    }
}