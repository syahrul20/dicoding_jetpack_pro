package com.sf.jetpack.mymov.network.repository.repocontract

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.network.response.*
import com.sf.jetpack.mymov.network.state.Resource

interface IMovieRepository {
    fun getListMoviePaging(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getDetailMovie(movieId: String): LiveData<MovieDetailResponse>
    fun getMovieCredit(movieId: String): LiveData<DataCreditResponse>
    fun getMovieRecommendations(movieId: String): LiveData<DataRecommendationsResponse>
}
