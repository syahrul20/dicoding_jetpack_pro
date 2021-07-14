package com.sf.jetpack.mymov.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
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
    private val tvShowRepository: ITvRepository,
    private val roomRepository: IRoomRepository
) : ViewModel() {
    var isLoading = MutableLiveData(true)
    val favoriteData = MutableLiveData<List<FavoriteEntity>>()

    fun getDetailMovieFromApi(
        movieId: String
    ): LiveData<MovieDetailResponse> = movieRepository.getDetailMovie(movieId)

    fun getMovieCredit(movieId: String): LiveData<DataCreditResponse> =
        movieRepository.getMovieCredit(movieId)

    fun getMovieRecommendations(movieId: String): LiveData<DataRecommendationsResponse> =
        movieRepository.getMovieRecommendations(movieId)

    fun getAllMovieFavorite() {
        viewModelScope.launch {
            val listFavorite = roomRepository.getListFavorite()
            listFavorite.filter { it.type == TYPE.TV_SHOW.name }
            favoriteData.value = listFavorite
        }
    }

    fun getTvShowFavorite() {
        viewModelScope.launch {
            val listFavorite = roomRepository.getListFavorite()
            listFavorite.filter { it.type == TYPE.TV_SHOW.name }
            favoriteData.value = listFavorite
        }
    }

    private fun insertFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            roomRepository.insertFavorite(favoriteEntity)
        }
    }

    private fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            roomRepository.deleteFavorite(favoriteEntity)
        }
    }

    fun <T> prepareDataToFavorite(data: T) {
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
                    insertFavorite(favoriteData)
                } else {
                    deleteFavorite(favoriteData)
                }
            }
            is TvResultList -> {
                data.type = TYPE.TV_SHOW.name
                val favoriteData = FavoriteEntity(
                    data.id,
                    data.name,
                    data.overview,
                    data.poster_path,
                    data.first_air_date,
                    data.vote_average,
                    data.isFavorite,
                    data.type!!
                )
                if (data.isFavorite == 1) {
                    insertFavorite(favoriteData)
                } else {
                    deleteFavorite(favoriteData)
                }
            }
            is FavoriteEntity -> {
                data.type = if (data is ListData) TYPE.MOVIE.name else TYPE.TV_SHOW.name
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
                    insertFavorite(favoriteData)
                } else {
                    deleteFavorite(favoriteData)
                }
            }
        }
    }

    fun getDetailTvFromApi(tvId: String): LiveData<TvDetailResponse> = tvShowRepository.getDetailTv(tvId)

    fun getTvShowCredit(tvId: String): LiveData<DataCreditResponse> =
        tvShowRepository.getTvShowCredit(tvId)

    fun getTvShowRecommendations(tvId: String): LiveData<DataRecommendationsResponse> =
        tvShowRepository.getTvShowRecommendations(tvId)
}