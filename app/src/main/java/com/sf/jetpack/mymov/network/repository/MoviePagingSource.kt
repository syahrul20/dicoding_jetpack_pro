package com.sf.jetpack.mymov.network.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.response.ListData
import retrofit2.*
import java.io.IOException

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

class MoviePagingSource(
    private val movieDataSource: MovieDataSource
) : PagingSource<Int, ListData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListData> {
        val pageIndex = params.key ?: 1
        return try {
            val response = movieDataSource.getListMoviePaging(pageIndex)
            val listMovie = mutableListOf<ListData>()
            val data = response.body()?.results ?: emptyList()
            listMovie.addAll(data)

            LoadResult.Page(
                data = listMovie,
                prevKey = if (pageIndex == 1) null else pageIndex - 1,
                nextKey = pageIndex.plus(1)
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ListData>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1) ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }
}