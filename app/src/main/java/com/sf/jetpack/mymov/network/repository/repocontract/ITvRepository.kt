package com.sf.jetpack.mymov.network.repository.repocontract

import androidx.lifecycle.LiveData
import com.sf.jetpack.mymov.network.response.*

interface ITvRepository {
    fun getListTvOnTheAir(): LiveData<TvResponse>
    fun getDetailTv(tvId: String): LiveData<TvDetailResponse>
    fun getTvShowCredit(tvId: String): LiveData<DataCreditResponse>
    fun getTvShowRecommendations(tvId: String): LiveData<DataRecommendationsResponse>
}