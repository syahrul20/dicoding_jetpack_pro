package com.sf.jetpack.mymov.network.repository.repocontract

import androidx.paging.PagingData
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.response.ListData
import kotlinx.coroutines.flow.Flow

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */
interface IMoviePagingRepository {
    fun getListMoviePaging(): Flow<PagingData<ListData>>
    fun getListMovieFavorite(): Flow<PagingData<FavoriteEntity>>
}
