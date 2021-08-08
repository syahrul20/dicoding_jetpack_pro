package com.sf.jetpack.mymov.utils

import com.sf.jetpack.mymov.network.datasource.MoviePagingDataSource
import com.sf.jetpack.mymov.network.response.MovieResponse
import kotlinx.coroutines.flow.flow

class FakeApi : MoviePagingDataSource {
    override suspend fun getListMoviePaging(page: Int, api_key: String): MovieResponse {
        return DummyData.generateListMovieResponse()
    }
}