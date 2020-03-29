package com.android.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.themoviedb.base.BaseViewModel
import com.android.themoviedb.helper.Resource
import com.android.themoviedb.model.*
import com.android.themoviedb.repository.MovieRepository

class MovieViewModel(repository: MovieRepository) : BaseViewModel() {

    private val nowPlayingRequest = MutableLiveData<MovieRequest>()
    private val popularRequest = MutableLiveData<MovieRequest>()
    private val upComingRequest = MutableLiveData<MovieRequest>()
    private val topRatedRequest = MutableLiveData<MovieRequest>()
    private val detailRequest = MutableLiveData<MovieDetailRequest>()
    private val reviewRequest = MutableLiveData<MovieReviewRequest>()
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

    val detailMovie: LiveData<Resource<MovieDetailResult>> = Transformations
        .switchMap(detailRequest) {
            repository.getDetailMovie(it)
        }

    fun getDetailMovie(request: MovieDetailRequest) {
        detailRequest.value = request
    }

    val reviewMovie: LiveData<Resource<MovieReviewResult>> = Transformations
        .switchMap(reviewRequest) {
            repository.getReviewMovie(it)
        }

    fun getReviewMovie(request: MovieReviewRequest) {
        reviewRequest.value = request
    }

    val configuration: LiveData<Resource<ConfirgurationResult>> = Transformations
        .switchMap(configurationRequest) {
            repository.getConfiguration(it)
        }

    fun getConfiguration(key: String) {
        configurationRequest.value = key
    }
}