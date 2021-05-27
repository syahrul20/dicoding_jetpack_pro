package com.sf.jetpack.mymov.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MovieResponse(
    val dates: Dates? = null,
    val page: Int = 0,
    val results: List<ListData> = arrayListOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0,
    val message: String? = ""
)

data class Dates(
    val maximum: String,
    val minimum: String
)

@Parcelize
data class ListData(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable