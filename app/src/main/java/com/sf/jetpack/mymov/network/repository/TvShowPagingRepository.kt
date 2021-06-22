package com.sf.jetpack.mymov.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.repository.repocontract.ITvShowPagingRepository
import com.sf.jetpack.mymov.network.response.TvResultList
import kotlinx.coroutines.flow.Flow

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */
class TvShowPagingRepository(private val tvDataSource: TvDataSource) : ITvShowPagingRepository {
    override fun getListTvOnAirPaging(): Flow<PagingData<TvResultList>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TvShowPagingSource(tvDataSource) }
        ).flow
    }
}