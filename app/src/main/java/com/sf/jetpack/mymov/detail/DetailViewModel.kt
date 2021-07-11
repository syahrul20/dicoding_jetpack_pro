package com.sf.jetpack.mymov.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.*
import com.sf.jetpack.mymov.utils.TYPE
import kotlinx.coroutines.launch

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi
 */

class DetailViewModel(
    private val movieRepository: IMovieRepository,
    private val tvShowRepository: ITvRepository
) : ViewModel() {
    var isLoading = MutableLiveData(true)
    val movieFavoriteData = MutableLiveData<List<FavoriteEntity>>()

    fun getDetailMovieFromApi(
        movieId: String
    ): LiveData<MovieDetailResponse> = movieRepository.getDetailMovie(movieId)

    fun getMovieCredit(movieId: String): LiveData<DataCreditResponse> =
        movieRepository.getMovieCredit(movieId)

    fun getMovieRecommendations(movieId: String): LiveData<DataRecommendationsResponse> =
        movieRepository.getMovieRecommendations(movieId)

    fun getAllMovieFavorite() {
        viewModelScope.launch {
            movieFavoriteData.value = movieRepository.getListMovieFavorite()
        }
    }

    private fun insertMovieFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            movieRepository.insertListMovieFavorite(favoriteEntity)
        }
    }

    private fun deleteMovieFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            movieRepository.deleteListMovieFavorite(favoriteEntity)
        }
    }

    fun <T> prepareDataToMovieFavorite(data: T) {
        when (data) {
            is ListData -> {
                data.type = TYPE.MOVIE.name
                val favoriteData = FavoriteEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.poster_path,
                    data.release_date,
                    data.vote_average,
                    data.isFavorite,
                    data.type!!
                )
                if (data.isFavorite == 1) {
                    insertMovieFavorite(favoriteData)
                } else {
                    deleteMovieFavorite(favoriteData)
                }
            }
            is FavoriteEntity -> {
                data.type = TYPE.MOVIE.name
                val favoriteData = FavoriteEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.poster_path,
                    data.release_date,
                    data.vote_average,
                    data.isFavorite,
                    data.type
                )
                if (data.isFavorite == 1) {
                    insertMovieFavorite(favoriteData)
                } else {
                    deleteMovieFavorite(favoriteData)
                }
            }
        }
    }

    fun getDetailTvFromApi(
        tvId: String
    ): LiveData<TvDetailResponse> = tvShowRepository.getDetailTv(tvId)

    fun getTvShowCredit(tvId: String): LiveData<DataCreditResponse> =
        tvShowRepository.getTvShowCredit(tvId)

    fun getTvShowRecommendations(tvId: String): LiveData<DataRecommendationsResponse> =
        tvShowRepository.getTvShowRecommendations(tvId)
}