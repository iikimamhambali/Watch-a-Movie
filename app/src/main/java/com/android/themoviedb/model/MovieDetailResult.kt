package com.android.themoviedb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_detail")
data class MovieDetailResult(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("belongs_to_collection") val belongsToCollection: BelongsCollection? = null,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genres: MutableList<Genre>? = mutableListOf(),
    @SerializedName("homepage") val homepage: String,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val productionCompanies: MutableList<ProductionCompany>? = mutableListOf(),
    @SerializedName("production_countries") val productionCountries: MutableList<ProductionCountry>? = mutableListOf(),
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("spoken_languages") val spokenLanguages: MutableList<SpokenLanguage>? = mutableListOf(),
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagLine: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)