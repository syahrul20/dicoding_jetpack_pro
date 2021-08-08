package com.sf.jetpack.mymov.network.datasource

import com.sf.jetpack.mymov.BuildConfig
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.network.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviePagingDataSource {
    @GET("movie/now_playing")
    suspend fun getListMoviePaging(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieResponse
}