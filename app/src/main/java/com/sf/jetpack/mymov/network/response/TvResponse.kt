package com.sf.jetpack.mymov.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class TvResponse(
    val results: List<TvResultList> = arrayListOf(),
    val message: String
)

@Parcelize
data class TvResultList(
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val vote_average: Double,
    val first_air_date: String = "",
    var isFavorite: Int = 0,
    var type: String? = ""
) : Parcelable