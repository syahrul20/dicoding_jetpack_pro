package com.sf.jetpack.mymov.data


data class TvShowsData(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val publishedYear: String,
    val linkTrailer: String?,
    val creator: String
)