package com.sf.jetpack.mymov.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvShowPagingRepository
import com.sf.jetpack.mymov.network.response.TvResponse

class TvShowViewModel(
    private val tvShowRepo: ITvRepository,
    private val tvShowPagingRepository: ITvShowPagingRepository
) : ViewModel() {

    fun getListTvShowPaging() = tvShowPagingRepository.getListTvOnAirPaging()

    fun getListTvShowFromApi(): LiveData<TvResponse> = tvShowRepo.getListTvOnTheAir()


}