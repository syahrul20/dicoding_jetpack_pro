package com.sf.jetpack.mymov.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sf.jetpack.mymov.db.FavoriteDao
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.*
import com.sf.jetpack.mymov.utils.API
import com.sf.jetpack.mymov.utils.EspressoIdleResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi
 */

class TvRepository(
    private val favoriteDao: FavoriteDao,
    private val tvDataSource: TvDataSource
) :
    ITvRepository {

    override fun getListTvOnTheAir(): LiveData<TvResponse> {
        val data = MutableLiveData<TvResponse>()
        val call = tvDataSource.getTvOnTheAir()
        EspressoIdleResource.increment()
        call.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                try {
                    data.value = response.body()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                EspressoIdleResource.decrement()
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                EspressoIdleResource.decrement()
                data.value = TvResponse(message = API.MESSAGE_FAIL)
            }
        })
        return data
    }

    override fun getDetailTv(tvId: String): LiveData<TvDetailResponse> {
        val data = MutableLiveData<TvDetailResponse>()
        val call = tvDataSource.getTvDetail(tvId)
        EspressoIdleResource.increment()
        call.enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(call: Call<TvDetailResponse>, response: Response<TvDetailResponse>) {
                try {
                    data.value = response.body()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                EspressoIdleResource.decrement()
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                data.value =  TvDetailResponse(
                    "",
                    arrayListOf(),
                    arrayListOf(),
                    "",
                    arrayListOf(),
                    "",
                    0,
                    true,
                    arrayListOf(),
                    "",
                    null,
                    "",
                    arrayListOf(),
                    null,
                    0,
                    0,
                    arrayListOf(),
                    "",
                    "",
                    "",
                    null,
                    "",
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf(),
                    "",
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
                data.value = DataCreditResponse(message =API.MESSAGE_FAIL, id = null)
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

    override suspend fun getListTvFavorite(): List<FavoriteEntity> {
        return withContext(Dispatchers.IO) {
            favoriteDao.getAll()
        }
    }
}