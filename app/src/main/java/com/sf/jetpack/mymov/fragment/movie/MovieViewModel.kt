package com.sf.jetpack.mymov.fragment.movie

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.response.MovieResponse
import kotlinx.coroutines.*


class MovieViewModel(
    private val movieRepository: IMovieRepository,
    private val moviePagingRepository: IMoviePagingRepository
) : ViewModel() {
    val listMovie = moviePagingRepository.getListMoviePaging().cachedIn(viewModelScope)
    val movieFavoriteData = MutableLiveData<List<FavoriteEntity>>()

    fun getListMovieFromApi(): LiveData<MovieResponse> = movieRepository.getListMovie()

    fun getAllMovieFavorite() {
        viewModelScope.launch {
            movieFavoriteData.value = movieRepository.getListMovieFavorite()
        }
    }

    fun insertMovieFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            movieRepository.insertListMovieFavorite(favoriteEntity)
        }
    }

    fun deleteMovieFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            movieRepository.deleteListMovieFavorite(favoriteEntity)
        }
    }
}