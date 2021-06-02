package com.sf.jetpack.mymov.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MovieResponse(
    val results: List<ListData> = arrayListOf(),
    val message: String? = ""
)

@Parcelize
data class ListData(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
) : Parcelable