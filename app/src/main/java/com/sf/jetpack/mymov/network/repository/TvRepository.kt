package com.sf.jetpack.mymov.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sf.jetpack.mymov.db.TVShowDao
import com.sf.jetpack.mymov.db.TvShowEntity
import com.sf.jetpack.mymov.network.NetworkBoundResource
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.*
import com.sf.jetpack.mymov.network.state.ApiResponse
import com.sf.jetpack.mymov.network.state.Resource
import com.sf.jetpack.mymov.utils.API
import com.sf.jetpack.mymov.utils.AppExecutors
import com.sf.jetpack.mymov.utils.EspressoIdleResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi
 */

class TvRepository(
    private val tvDataSource: TvDataSource,
    private val tvShowDao: TVShowDao,
    private val appExecutors: AppExecutors
) :
    ITvRepository {

    override fun getListTvShowPaging(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, TvResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(tvShowDao.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<TvResponse>> {
                val data = MutableLiveData<ApiResponse<TvResponse>>()
                val call = tvDataSource.getTvOnAirPaging(1)
                EspressoIdleResource.increment()
                call.enqueue(object : Callback<TvResponse> {
                    override fun onResponse(
                        call: Call<TvResponse>,
                        response: Response<TvResponse>
                    ) {
                        try {
                            data.value = ApiResponse.success(response.body()!!)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        EspressoIdleResource.decrement()
                    }

                    override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                        EspressoIdleResource.decrement()
                        data.value = ApiResponse.error(
                            "Fail", TvResponse(
                                arrayListOf(),
                                API.MESSAGE_FAIL
                            )
                        )
                    }
                })
                return data
            }

            override fun saveCallResult(data: TvResponse) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data.results) {
                    val course = TvShowEntity(
                        response.id,
                        response.name,
                        response.overview,
                        response.poster_path,
                        response.first_air_date,
                        response.vote_average,
                        response.isFavorite,
                    )
                    tvShowList.add(course)
                }
                tvShowDao.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getListTvShowFavoritePaging(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(tvShowDao.getAllTvShowFavorite(), config).build()
    }

    override fun saveFavoriteTvShow(tvShowEntity: TvShowEntity) {
        appExecutors.diskIO().execute { tvShowDao.updateTvShowEntities(tvShowEntity) }
    }

    override fun getDetailTv(tvId: String): LiveData<TvDetailResponse> {
        val data = MutableLiveData<TvDetailResponse>()
        val call = tvDataSource.getTvDetail(tvId)
        EspressoIdleResource.increment()
        call.enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(
                call: Call<TvDetailResponse>,
                response: Response<TvDetailResponse>
            ) {
                try {
                    data.value = response.body()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                EspressoIdleResource.decrement()
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                data.value = TvDetailResponse(
                    "",
                    arrayListOf(),
                    0,
                    "",
                    "",
                    "",
                    null,
                    "",
                    "",
                    null,
                    0,
                    API.MESSAGE_FAIL
                )
            }
        })
        return data
    }

    override fun getTvShowCredit(tvId: String): LiveData<DataCreditResponse> {
        val data = MutableLiveData<DataCreditResponse>()
        val call = tvDataSource.getTvShowCredit(tvId)
        EspressoIdleResource.increment()
        call.enqueue(object : Callback<DataCreditResponse> {
            override fun onResponse(
                call: Call<DataCreditResponse>,
                response: Response<DataCreditResponse>
            ) {
                try {
                    data.value = response.body()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                EspressoIdleResource.decrement()
            }

            override fun onFailure(call: Call<DataCreditResponse>, t: Throwable) {
                data.value = DataCreditResponse(message = API.MESSAGE_FAIL, id = null)
            }
        })
        return data
    }

    override fun getTvShowRecommendations(tvId: String): LiveData<DataRecommendationsResponse> {
        val data = MutableLiveData<DataRecommendationsResponse>()
        val call = tvDataSource.getTvShowRecommendations(tvId)
        EspressoIdleResource.increment()
        call.enqueue(object : Callback<DataRecommendationsResponse> {
            override fun onResponse(
                call: Call<DataRecommendationsResponse>,
                response: Response<DataRecommendationsResponse>
            ) {
                try {
                    data.value = response.body()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                EspressoIdleResource.decrement()
            }

            override fun onFailure(call: Call<DataRecommendationsResponse>, t: Throwable) {
                data.value = DataRecommendationsResponse(
                    message = API.MESSAGE_FAIL,
                    totalPages = null,
                    totalResults = null
                )
            }
        })
        return data
    }
}