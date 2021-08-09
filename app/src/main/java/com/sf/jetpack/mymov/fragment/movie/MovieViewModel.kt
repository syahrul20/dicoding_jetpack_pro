package com.sf.jetpack.mymov.fragment.movie

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.response.MovieResponse
import com.sf.jetpack.mymov.network.state.Resource


class MovieViewModel(
    private val movieRepository :IMovieRepository
) : ViewModel() {

    fun getListMoviePaging(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getListMoviePaging()

    fun getListMovieFavoritePaging(): LiveData<PagedList<MovieEntity>> = movieRepository.getListMovieFavoritePaging()

    fun saveFavoriteMovie(movie: MovieEntity) {
        movieRepository.saveFavoriteMovie(movie)
    }

//    fun getAllMovieFavorite() {
//        viewModelScope.launch {
//            movieFavoriteData.value = roomRepository.getListFavorite()
//        }
//    }

//    fun deleteMovieFavorite(favoriteEntity: FavoriteEntity) {
//        viewModelScope.launch {
//            roomRepository.deleteFavorite(favoriteEntity)
//        }
//    }
}