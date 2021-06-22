package com.sf.jetpack.mymov.network.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.response.TvResultList
import retrofit2.*
import java.io.IOException

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

class TvShowPagingSource(
    private val tvShowDataSource: TvDataSource
) : PagingSource<Int, TvResultList>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvResultList> {
        val pageIndex = params.key ?: 1
        return try {
            val response = tvShowDataSource.getTvOnAirPaging(pageIndex)
            val listTvShow = mutableListOf<TvResultList>()
            val data = response.body()?.results ?: emptyList()
            listTvShow.addAll(data)

            LoadResult.Page(
                data = listTvShow,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = pageIndex.plus(1)
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TvResultList>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1) ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}