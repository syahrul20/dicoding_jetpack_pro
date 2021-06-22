package com.sf.jetpack.mymov.network.repository.repocontract

import androidx.paging.PagingData
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.network.response.TvResultList
import kotlinx.coroutines.flow.Flow

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */
interface ITvShowPagingRepository {
    fun getListTvOnAirPaging(): Flow<PagingData<TvResultList>>
}
