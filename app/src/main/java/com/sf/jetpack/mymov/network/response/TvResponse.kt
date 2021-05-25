package com.sf.jetpack.mymov.network.response

data class TvResponse(
    val page: Int = 0,
    val results: List<TvResultList> = arrayListOf(),
    val total_pages: Int? = null,
    val total_results: Int? = null,
    val message: String
)

data class TvResultList(
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)