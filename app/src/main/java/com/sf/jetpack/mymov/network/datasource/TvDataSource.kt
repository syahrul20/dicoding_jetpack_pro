package com.sf.jetpack.mymov.network.datasource

import com.sf.jetpack.mymov.BuildConfig
import com.sf.jetpack.mymov.network.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvDataSource {
    @GET("tv/on_the_air")
    fun getTvOnAirPaging(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Call<TvResponse>

    @GET("tv/{tvId}")
    fun getTvDetail(
        @Path("tvId") tvId: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Call<TvDetailResponse>

    @GET("tv/{tvId}/credits")
    fun getTvShowCredit(
        @Path("tvId") tvId: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Call<DataCreditResponse>

    @GET("tv/{tvId}/recommendations")
    fun getTvShowRecommendations(
        @Path("tvId") tvId: String,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Call<DataRecommendationsResponse>
}