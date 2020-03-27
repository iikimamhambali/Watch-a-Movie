package com.android.themoviedb.repository

import androidx.lifecycle.LiveData
import com.android.themoviedb.base.BaseRepo
import com.android.themoviedb.helper.ApiResponse
import com.android.themoviedb.helper.AppExecutors
import com.android.themoviedb.helper.Resource
import com.android.themoviedb.model.*
import com.android.themoviedb.network.MovieServices

class MovieRepository(
    private val appExecutors: AppExecutors,
    private val services: MovieServices
) {

    fun getNowPlaying(request: MovieRequest): LiveData<Resource<MovieResult>> {
        return object : BaseRepo<MovieResult>(appExecutors) {
            override fun loadFromNetwork(): LiveData<ApiResponse<MovieResult>> {
                return services.getNowPlaying(
                    request.apiKey,
                    request.language,
                    request.page,
                    request.region
                )
            }
        }.asLiveData()
    }

    fun getPopular(request: MovieRequest): LiveData<Resource<MovieResult>> {
        return object : BaseRepo<MovieResult>(appExecutors) {
            override fun loadFromNetwork(): LiveData<ApiResponse<MovieResult>> {
                return services.getPopular(
                    request.apiKey,
                    request.language,
                    request.page,
                    request.region
                )
            }
        }.asLiveData()
    }

    fun getUpComing(request: MovieRequest): LiveData<Resource<MovieResult>> {
        return object : BaseRepo<MovieResult>(appExecutors) {
            override fun loadFromNetwork(): LiveData<ApiResponse<MovieResult>> {
                return services.getUpComing(
                    request.apiKey,
                    request.language,
                    request.page,
                    request.region
                )
            }
        }.asLiveData()
    }

    fun getTopRated(request: MovieRequest): LiveData<Resource<MovieResult>> {
        return object : BaseRepo<MovieResult>(appExecutors) {
            override fun loadFromNetwork(): LiveData<ApiResponse<MovieResult>> {
                return services.getTopRated(
                    request.apiKey,
                    request.language,
                    request.page,
                    request.region
                )
            }
        }.asLiveData()
    }

    fun getDetailMovie(request: MovieDetailRequest): LiveData<Resource<MovieDetailResult>> {
        return object : BaseRepo<MovieDetailResult>(appExecutors) {
            override fun loadFromNetwork(): LiveData<ApiResponse<MovieDetailResult>> {
                return services.getDetailMovie(
                    request.movieId,
                    request.apiKey,
                    request.language,
                    request.appendRoResponse
                )
            }

        }.asLiveData()
    }

    fun getConfiguration(key: String): LiveData<Resource<ConfirgurationResult>> {
        return object : BaseRepo<ConfirgurationResult>(appExecutors) {
            override fun loadFromNetwork(): LiveData<ApiResponse<ConfirgurationResult>> {
                return services.getConfiguration(key)
            }
        }.asLiveData()
    }
}