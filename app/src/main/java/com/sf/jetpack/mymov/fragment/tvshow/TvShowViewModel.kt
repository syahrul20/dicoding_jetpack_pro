package com.sf.jetpack.mymov.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvShowPagingRepository
import com.sf.jetpack.mymov.network.response.TvResponse
import kotlinx.coroutines.launch

class TvShowViewModel(
    private val tvShowRepo: ITvRepository,
    private val tvShowPagingRepository: ITvShowPagingRepository
) : ViewModel() {

    fun getListTvShowPaging() = tvShowPagingRepository.getListTvOnAirPaging()

    fun getListTvShowFromApi(): LiveData<TvResponse> = tvShowRepo.getListTvOnTheAir()

    fun getAllFavoriteTvShow() : LiveData<List<FavoriteEntity>>{
        val data = MutableLiveData<List<FavoriteEntity>>()
        viewModelScope.launch {
            data.value = tvShowRepo.getListTvFavorite()
        }
        return data
    }
}