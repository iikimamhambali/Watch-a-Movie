package com.android.themoviedb.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class LatestResult(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: Objects,
    @SerializedName("belongs_to_collection") val belongsCollection: Objects,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genres: MutableList<String>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imbId: Objects,
    @SerializedName("original_language") val originLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("poster_path") val posterPath: Objects,
    @SerializedName("production_companies") val productCompanies: MutableList<Objects>,
    @SerializedName("production_countries") val productCountries: MutableList<Objects>,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("spoken_languages") val spokenLanguages: MutableList<Objects>,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagLine: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Int,
    @SerializedName("vote_count") val voteCount: Int
)