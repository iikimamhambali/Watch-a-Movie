package com.android.themoviedb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailAdapter(
    var adult: Boolean? = false,
    var id: Int? = 0,
    var backdropPath: String? = "",
    var belongsToCollection: BelongsCollection? = null,
    var budget: Int = 0,
    var genres: MutableList<Genre>? = mutableListOf(),
    var homepage: String? = "",
    var imdbId: String? = "",
    var originalLanguage: String? = "",
    var originalTitle: String? = "",
    var overview: String? = "",
    var popularity: Double? = 0.0,
    var posterPath: String? = "",
    var productionCompanies: MutableList<ProductionCompany>? = mutableListOf(),
    var productionCountries: MutableList<ProductionCountry>? = mutableListOf(),
    var releaseDate: String? = "",
    var revenue: Int? = 0,
    var runtime: Int? = 0,
    var spokenLanguages: MutableList<SpokenLanguage>? = mutableListOf(),
    var status: String? = "",
    var tagLine: String? = "",
    var title: String? = "",
    var video: Boolean? = false,
    var voteAverage: Double? = 0.0,
    var voteCount: Int? = 0
) : Parcelable