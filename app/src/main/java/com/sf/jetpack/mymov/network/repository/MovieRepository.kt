package com.sf.jetpack.mymov.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.response.*
import com.sf.jetpack.mymov.utils.API
import com.sf.jetpack.mymov.utils.EspressoIdleResource
import retrofit2.*
import java.lang.Exception

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi
 */

class MovieRepository(
    private val movieDataSource: MovieDataSource
) :
    IMovieRepository {

    override fun getDetailMovie(movieId: String): LiveData<MovieDetailResponse> {
        val data = MutableLiveData<MovieDetailResponse>()
        val call = movieDataSource.getDetailMovie(movieId)
        EspressoIdleResource.increment()
        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                try {
                    data.value = response.body()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                EspressoIdleResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                EspressoIdleResource.decrement()
                Log.i("Fail", t.message.toString())
                data.value = MovieDetailResponse(
                    false,
                    "",
                    null,
                    0,
                    arrayListOf(),
                    "",
                    0,
                    "",
                    "",
                    "",
                    "",
                    null,
                    "",
                    arrayListOf(),
                    arrayListOf(),
                    "",
                    0,
                    0,
                    arrayListOf(),
                    "",
                    "",
                    "",
                    false,
                    null,
                    0,
                    API.MESSAGE_FAIL
                )
            }
        })
        return data
    }

    override fun getMovieCredit(movieId: String): LiveData<DataCreditResponse> {
        val data = MutableLiveData<DataCreditResponse>()
        val call = movieDataSource.getMovieCredit(movieId)
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
                EspressoIdleResource.decrement()
                data.value = DataCreditResponse(message = API.MESSAGE_FAIL, id = null)
            }
        })
        return data
    }

    override fun getMovieRecommendations(movieId: String): LiveData<DataRecommendationsResponse> {
        val data = MutableLiveData<DataRecommendationsResponse>()
        val call = movieDataSource.getMovieRecommendation(movieId)
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
                EspressoIdleResource.decrement()
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