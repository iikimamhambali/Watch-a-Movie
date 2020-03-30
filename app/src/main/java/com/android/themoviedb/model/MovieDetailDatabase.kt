package com.android.themoviedb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_detail")
data class MovieDetailDatabase(
    @ColumnInfo(name = "adult") var adult: Boolean? = false,
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int? = 0,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String? = "",
    @ColumnInfo(name = "belongs_to_collection") var belongsToCollection: BelongsCollection? = null,
    @ColumnInfo(name = "budget") var budget: Int = 0,
    @ColumnInfo(name = "genres") var genres: MutableList<Genre> = mutableListOf(),
    @ColumnInfo(name = "homepage") var homepage: String? = "",
    @ColumnInfo(name = "imdb_id") var imdbId: String? = "",
    @ColumnInfo(name = "original_language") var originalLanguage: String? = "",
    @ColumnInfo(name = "original_title") var originalTitle: String? = "",
    @ColumnInfo(name = "overview") var overview: String? = "",
    @ColumnInfo(name = "popularity") var popularity: Double? = 0.0,
    @ColumnInfo(name = "poster_path") var posterPath: String? = "",
    @ColumnInfo(name = "production_companies") var productionCompanies: MutableList<ProductionCompany> = mutableListOf(),
    @ColumnInfo(name = "production_countries") var productionCountries: MutableList<ProductionCountry> = mutableListOf(),
    @ColumnInfo(name = "release_date") var releaseDate: String? = "",
    @ColumnInfo(name = "revenue") var revenue: Int? = 0,
    @ColumnInfo(name = "runtime") var runtime: Int? = 0,
    @ColumnInfo(name = "spoken_languages") var spokenLanguages: MutableList<SpokenLanguage> = mutableListOf(),
    @ColumnInfo(name = "status") var status: String? = "",
    @ColumnInfo(name = "tagline") var tagLine: String? = "",
    @ColumnInfo(name = "title") var title: String? = "",
    @ColumnInfo(name = "video") var video: Boolean? = false,
    @ColumnInfo(name = "vote_average") var voteAverage: Double? = 0.0,
    @ColumnInfo(name = "vote_count") var voteCount: Int? = 0
)