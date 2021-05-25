package com.sf.jetpack.mymov.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.data.TvShowsData
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.TvResponse
import com.sf.jetpack.mymov.network.response.TvResultList
import com.sf.jetpack.mymov.utils.DummyData

class TvShowViewModel(private val tvShowRepo: ITvRepository) : ViewModel() {

    fun getListTvShow(): List<TvShowsData> {
        return DummyData.generateTvShowListData()
    }

    fun getListTvShowFromApi(): LiveData<TvResponse> = tvShowRepo.getListTvOnTheAir()
}