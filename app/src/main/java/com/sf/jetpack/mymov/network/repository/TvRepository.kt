package com.sf.jetpack.mymov.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.*
import com.sf.jetpack.mymov.utils.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvRepository(
    private val tvDataSource: TvDataSource
) :
    ITvRepository {

    override fun getListTvOnTheAir(): LiveData<TvResponse> {
        val data = MutableLiveData<TvResponse>()
        val call = tvDataSource.getTvOnTheAir()
        call.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.i("Fail", t.message.toString())
                data.value = TvResponse(message = "Gagal mengambil data")
            }
        })
        return data
    }

    override fun getDetailTv(tvId: String): LiveData<TvDetailResponse> {
        val data = MutableLiveData<TvDetailResponse>()
        val call = tvDataSource.getTvDetail(tvId)
        call.enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(call: Call<TvDetailResponse>, response: Response<TvDetailResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                Log.i("Fail", t.message.toString())
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
        call.enqueue(object : Callback<DataCreditResponse> {
            override fun onResponse(
                call: Call<DataCreditResponse>,
                response: Response<DataCreditResponse>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<DataCreditResponse>, t: Throwable) {
                Log.i("Fail", t.message.toString())
                data.value = DataCreditResponse(message = "Gagal mengambil data", id = null)
            }
        })
        return data
    }

    override fun getTvShowRecommendations(tvId: String): LiveData<DataRecommendationsResponse> {
        val data = MutableLiveData<DataRecommendationsResponse>()
        val call = tvDataSource.getTvShowRecommendations(tvId)
        call.enqueue(object : Callback<DataRecommendationsResponse> {
            override fun onResponse(
                call: Call<DataRecommendationsResponse>,
                response: Response<DataRecommendationsResponse>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<DataRecommendationsResponse>, t: Throwable) {
                Log.i("Fail", t.message.toString())
                data.value = DataRecommendationsResponse(
                    message = "Gagal mengambil data",
                    totalPages = null,
                    totalResults = null
                )
            }
        })
        return data
    }
}