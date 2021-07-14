package com.sf.jetpack.mymov.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvShowPagingRepository
import com.sf.jetpack.mymov.network.response.TvResponse
import kotlinx.coroutines.launch

class TvShowViewModel(
    private val tvShowRepository: ITvRepository,
    private val tvShowPagingRepository: ITvShowPagingRepository,
    private val roomRepository: IRoomRepository
) : ViewModel() {
    val tvShowFavoriteData = MutableLiveData<List<FavoriteEntity>>()

    fun getListTvShowPaging() = tvShowPagingRepository.getListTvOnAirPaging()

    fun getListTvShowFromApi(): LiveData<TvResponse> = tvShowRepository.getListTvOnTheAir()

    fun getListFavoriteTvShow() = tvShowPagingRepository.getListTvShowFavorite().cachedIn(viewModelScope)

    fun getAllFavorite() {
        viewModelScope.launch {
            tvShowFavoriteData.value = roomRepository.getListFavorite()
        }
    }

    fun deleteMovieFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            roomRepository.deleteFavorite(favoriteEntity)
        }
    }
}