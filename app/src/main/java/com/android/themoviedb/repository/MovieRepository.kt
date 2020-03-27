package com.android.themoviedb.repository

import androidx.lifecycle.LiveData
import com.android.themoviedb.base.BaseRepo
import com.android.themoviedb.helper.ApiResponse
import com.android.themoviedb.helper.AppExecutors
import com.android.themoviedb.helper.Resource
import com.android.themoviedb.model.ConfirgurationResult
import com.android.themoviedb.model.NowPlayingRequest
import com.android.themoviedb.model.MovieResult
import com.android.themoviedb.network.MovieServices

class MovieRepository(
    private val appExecutors: AppExecutors,
    private val services: MovieServices
) {

    fun getNowPlaying(request: NowPlayingRequest): LiveData<Resource<MovieResult>> {
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

    fun getConfiguration(key: String): LiveData<Resource<ConfirgurationResult>> {
        return object : BaseRepo<ConfirgurationResult>(appExecutors) {
            override fun loadFromNetwork(): LiveData<ApiResponse<ConfirgurationResult>> {
                return services.getConfiguration(key)
            }
        }.asLiveData()
    }
}