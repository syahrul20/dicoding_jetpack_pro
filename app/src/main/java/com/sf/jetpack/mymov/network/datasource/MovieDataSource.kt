package com.sf.jetpack.mymov.network.datasource

import com.sf.jetpack.mymov.BuildConfig.API_KEY
import com.sf.jetpack.mymov.network.response.MovieCreditResponse
import com.sf.jetpack.mymov.network.response.MovieDetailResponse
import com.sf.jetpack.mymov.network.response.MovieRecommendationResponse
import com.sf.jetpack.mymov.network.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDataSource {
    @GET("movie/now_playing")
    fun getListMovie(
        @Query("api_key") api_key: String = API_KEY
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: String,
        @Query("api_key") api_key: String = API_KEY
    ): Call<MovieDetailResponse>

    @GET("movie/{id}/credits")
    fun getMovieCredit(
        @Path("id") id: String,
        @Query("api_key") api_key: String = API_KEY
    ): Call<MovieCreditResponse>

    @GET("movie/{id}/recommendations")
    fun getMovieRecomendation(
        @Path("id") id: String,
        @Query("api_key") api_key: String = API_KEY
    ): Call<MovieRecommendationResponse>
}