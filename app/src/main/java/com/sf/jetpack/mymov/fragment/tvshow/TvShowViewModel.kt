package com.sf.jetpack.mymov.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.TvResponse

class TvShowViewModel(private val tvShowRepo: ITvRepository) : ViewModel() {

    fun getListTvShowFromApi(): LiveData<TvResponse> = tvShowRepo.getListTvOnTheAir()
}