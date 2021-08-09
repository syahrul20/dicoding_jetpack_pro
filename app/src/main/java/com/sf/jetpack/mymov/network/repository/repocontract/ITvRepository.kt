package com.sf.jetpack.mymov.network.repository.repocontract

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sf.jetpack.mymov.db.TvShowEntity
import com.sf.jetpack.mymov.network.response.*
import com.sf.jetpack.mymov.network.state.Resource

interface ITvRepository {
    fun getListTvShowPaging(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getDetailTv(tvId: String): LiveData<TvDetailResponse>
    fun getTvShowCredit(tvId: String): LiveData<DataCreditResponse>
    fun getTvShowRecommendations(tvId: String): LiveData<DataRecommendationsResponse>
}