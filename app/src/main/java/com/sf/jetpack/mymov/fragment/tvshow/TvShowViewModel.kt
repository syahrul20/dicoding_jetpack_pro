package com.sf.jetpack.mymov.fragment.tvshow

import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.data.TvShowsData
import com.sf.jetpack.mymov.utils.DummyData

class TvShowViewModel : ViewModel() {

    fun getListTvShow(): List<TvShowsData> {
        return DummyData.generateTvShowListData()
    }
}