package com.sf.jetpack.mymov.network.repository.repocontract

import androidx.lifecycle.LiveData
import com.sf.jetpack.mymov.network.response.ListData

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */
interface IMoviePagingRepository {
    fun getListMoviePaging(): LiveData<ListData>
//    fun getListMovieFavorite(): LiveData<FavoriteEntity>
}
