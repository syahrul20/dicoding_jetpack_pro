package com.sf.jetpack.mymov.network.repository.repocontract

import androidx.lifecycle.LiveData
import com.sf.jetpack.mymov.network.response.DataCreditResponse
import com.sf.jetpack.mymov.network.response.MovieDetailResponse
import com.sf.jetpack.mymov.network.response.DataRecommendationsResponse
import com.sf.jetpack.mymov.network.response.MovieResponse

interface IMovieRepository {
    fun getListMovie(): LiveData<MovieResponse>
    fun getDetailMovie(movieId: String): LiveData<MovieDetailResponse>
    fun getMovieCredit(movieId: String): LiveData<DataCreditResponse>
    fun getMovieRecommendations(movieId: String): LiveData<DataRecommendationsResponse>
}