package com.android.themoviedb.model

import com.android.themoviedb.helper.Mapper

class MovieDetailAdapterMapper : Mapper<MovieDetailDatabase, MovieDetailAdapter> {

    override fun transform(from: MovieDetailDatabase): MovieDetailAdapter {
        val movieDetailAdapter = MovieDetailAdapter()
        movieDetailAdapter.adult = from.adult
        movieDetailAdapter.backdropPath = from.backdropPath
        movieDetailAdapter.genres = from.genres
        movieDetailAdapter.id = from.id
        movieDetailAdapter.originalTitle = from.originalTitle
        movieDetailAdapter.overview = from.overview
        movieDetailAdapter.posterPath = from.posterPath
        movieDetailAdapter.releaseDate = from.releaseDate
        movieDetailAdapter.title = from.title
        movieDetailAdapter.voteAverage = from.voteAverage
        movieDetailAdapter.voteCount = from.voteCount
        return movieDetailAdapter
    }
}