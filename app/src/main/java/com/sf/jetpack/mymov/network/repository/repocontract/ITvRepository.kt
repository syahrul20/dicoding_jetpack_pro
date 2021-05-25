package com.sf.jetpack.mymov.network.repository.repocontract

import androidx.lifecycle.LiveData
import com.sf.jetpack.mymov.network.response.TvDetailResponse
import com.sf.jetpack.mymov.network.response.TvResponse

interface ITvRepository {
    fun getListTvOnTheAir(): LiveData<TvResponse>
    fun getDetailTv(tvId: String): LiveData<TvDetailResponse>
}