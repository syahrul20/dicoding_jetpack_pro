package com.sf.jetpack.mymov.fragment.movie

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import kotlinx.coroutines.*


class MovieViewModel(
    private val roomRepository: IRoomRepository,
    private val moviePagingRepository: IMoviePagingRepository
) : ViewModel() {

    val movieFavoriteData = MutableLiveData<List<FavoriteEntity>>()

    fun getListMoviePaging() = moviePagingRepository.getListMoviePaging()

    fun listMovieFavorite() = moviePagingRepository.getListMovieFavorite()

    fun getAllMovieFavorite() {
        viewModelScope.launch {
            movieFavoriteData.value = roomRepository.getListFavorite()
        }
    }

    fun deleteMovieFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            roomRepository.deleteFavorite(favoriteEntity)
        }
    }
}