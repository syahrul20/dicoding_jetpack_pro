package com.sf.jetpack.mymov.network.repository.repocontract

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.response.*
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getListMovie(): LiveData<MovieResponse>
    fun getDetailMovie(movieId: String): LiveData<MovieDetailResponse>
    fun getMovieCredit(movieId: String): LiveData<DataCreditResponse>
    fun getMovieRecommendations(movieId: String): LiveData<DataRecommendationsResponse>
    suspend fun getListMovieFavorite(): List<FavoriteEntity>
    suspend fun deleteListMovieFavorite(favoriteEntity: FavoriteEntity)
    suspend fun insertListMovieFavorite(favoriteEntity: FavoriteEntity)
}
