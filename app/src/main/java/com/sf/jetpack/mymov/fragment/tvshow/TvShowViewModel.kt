package com.sf.jetpack.mymov.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.db.TvShowEntity
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.state.Resource

class TvShowViewModel(
    private val tvShowRepository: ITvRepository,
) : ViewModel() {

    fun getListTvShowPaging(): LiveData<Resource<PagedList<TvShowEntity>>> = tvShowRepository.getListTvShowPaging()
}