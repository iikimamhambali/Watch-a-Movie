package com.android.themoviedb.model

import com.android.themoviedb.helper.Mapper

class MovieDetailMapper : Mapper<MovieDetailResult, MovieDetailDatabase> {

    override fun transform(from: MovieDetailResult): MovieDetailDatabase {

        val movieDetail = MovieDetailDatabase()
        movieDetail.adult = from.adult
        movieDetail.backdropPath = from.backdropPath
        movieDetail.genres = from.genres
        movieDetail.id = from.id
        movieDetail.originalTitle = from.originalTitle
        movieDetail.overview = from.overview
        movieDetail.posterPath = from.posterPath
        movieDetail.releaseDate = from.releaseDate
        movieDetail.title = from.title
        movieDetail.voteAverage = from.voteAverage
        movieDetail.voteCount = from.voteCount

        return movieDetail
    }
}