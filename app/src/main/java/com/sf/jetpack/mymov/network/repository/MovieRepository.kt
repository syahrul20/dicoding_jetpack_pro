package com.sf.jetpack.mymov.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.response.MovieCreditResponse
import com.sf.jetpack.mymov.network.response.MovieDetailResponse
import com.sf.jetpack.mymov.network.response.MovieRecommendationResponse
import com.sf.jetpack.mymov.network.response.MovieResponse
import com.sf.jetpack.mymov.utils.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(
    private val movieDataSource: MovieDataSource
) :
    IMovieRepository {

    override fun getListMovie(): LiveData<MovieResponse> {
        val data = MutableLiveData<MovieResponse>()
        val call = movieDataSource.getListMovie()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.i("Fail", t.message.toString())
                data.value = MovieResponse(message = "Gagal mengambil data")
            }
        })
        return data
    }

    override fun getDetailMovie(movieId: String): LiveData<MovieDetailResponse> {
        val data = MutableLiveData<MovieDetailResponse>()
        val call = movieDataSource.getDetailMovie(movieId)
        call.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                data.value = response.body() as MovieDetailResponse
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
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

    override fun getMovieCredit(movieId: String): LiveData<MovieCreditResponse> {
        val data = MutableLiveData<MovieCreditResponse>()
        val call = movieDataSource.getMovieCredit(movieId)
        call.enqueue(object : Callback<MovieCreditResponse> {
            override fun onResponse(
                call: Call<MovieCreditResponse>,
                response: Response<MovieCreditResponse>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<MovieCreditResponse>, t: Throwable) {
                Log.i("Fail", t.message.toString())
                data.value = MovieCreditResponse(message = "Gagal mengambil data", id = null)
            }
        })
        return data
    }

    override fun getMovieRecommendations(movieId: String): LiveData<MovieRecommendationResponse> {
        val data = MutableLiveData<MovieRecommendationResponse>()
        val call = movieDataSource.getMovieRecomendation(movieId)
        call.enqueue(object : Callback<MovieRecommendationResponse> {
            override fun onResponse(
                call: Call<MovieRecommendationResponse>,
                response: Response<MovieRecommendationResponse>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<MovieRecommendationResponse>, t: Throwable) {
                Log.i("Fail", t.message.toString())
                data.value = MovieRecommendationResponse(
                    message = "Gagal mengambil data",
                    totalPages = null,
                    totalResults = null
                )
            }
        })
        return data
    }
}