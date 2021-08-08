package com.sf.jetpack.mymov.fragment.movie

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.network.repository.MovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import com.sf.jetpack.mymov.network.response.MovieResponse
import com.sf.jetpack.mymov.network.state.Resource


class MovieViewModel(
    private val movieRepository :IMovieRepository
) : ViewModel() {
    val movieResponse = MutableLiveData<MovieResponse>()

    fun getListMoviePaging(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getListMoviePaging()

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