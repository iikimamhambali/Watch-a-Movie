package com.android.themoviedb.network

import androidx.lifecycle.LiveData
import com.android.themoviedb.helper.ApiResponse
import com.android.themoviedb.model.ConfirgurationResult
import com.android.themoviedb.model.LatestResult
import com.android.themoviedb.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServices {

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("region") region: String
    ): LiveData<ApiResponse<MovieResult>>

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("region") region: String
    ): LiveData<ApiResponse<MovieResult>>

    @GET("movie/upcoming")
    fun getUpComing(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("region") region: String
    ): LiveData<ApiResponse<MovieResult>>

    @GET("configuration")
    fun getConfiguration(@Query("api_key") key: String): LiveData<ApiResponse<ConfirgurationResult>>
}