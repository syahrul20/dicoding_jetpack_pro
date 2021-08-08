package com.sf.jetpack.mymov.fragment.tvshow

import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository

class TvShowViewModel(
    private val tvShowRepository: ITvRepository,
) : ViewModel() {
//    val tvShowFavoriteData = MutableLiveData<List<FavoriteEntity>>()
//
//    fun getListTvShowPaging() = tvShowPagingRepository.getListTvOnAirPaging()
//
//    fun getListTvShowFromApi(): LiveData<TvResponse> = tvShowRepository.getListTvOnTheAir()
//
//    fun getListFavoriteTvShow() = tvShowPagingRepository.getListTvShowFavorite().cachedIn(viewModelScope)
//
//    fun getAllFavorite() {
//        viewModelScope.launch {
//            tvShowFavoriteData.value = roomRepository.getListFavorite()
//        }
//    }
//
//    fun deleteMovieFavorite(favoriteEntity: FavoriteEntity) {
//        viewModelScope.launch {
//            roomRepository.deleteFavorite(favoriteEntity)
//        }
//    }
}