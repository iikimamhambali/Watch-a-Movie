package com.android.themoviedb.network

import androidx.lifecycle.LiveData
import com.android.themoviedb.helper.ApiResponse
import com.android.themoviedb.model.ConfirgurationResult
import com.android.themoviedb.model.MovieDetailResult
import com.android.themoviedb.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("region") region: String
    ): LiveData<ApiResponse<MovieResult>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("append_to_response") appendToResponse: String
    ): LiveData<ApiResponse<MovieDetailResult>>

    @GET("configuration")
    fun getConfiguration(@Query("api_key") key: String): LiveData<ApiResponse<ConfirgurationResult>>
}