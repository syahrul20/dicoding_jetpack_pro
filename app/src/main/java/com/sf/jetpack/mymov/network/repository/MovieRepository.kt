package com.sf.jetpack.mymov.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sf.jetpack.mymov.db.MovieDao
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.network.NetworkBoundResource
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.response.*
import com.sf.jetpack.mymov.network.state.ApiResponse
import com.sf.jetpack.mymov.network.state.Resource
import com.sf.jetpack.mymov.utils.API
import com.sf.jetpack.mymov.utils.AppExecutors
import com.sf.jetpack.mymov.utils.EspressoIdleResource
import retrofit2.*
import java.lang.Exception

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi
 */

class MovieRepository(
    private val movieDataSource: MovieDataSource,
    private val movieDao: MovieDao,
    private val appExecutors: AppExecutors
) :
    IMovieRepository {

    override fun getListMoviePaging(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, MovieResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(movieDao.getAllMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                val data = MutableLiveData<ApiResponse<MovieResponse>>()
                val call = movieDataSource.getListMoviePaging(1)
                EspressoIdleResource.increment()
                call.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        try {
                            data.value = ApiResponse.success(response.body()!!)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        EspressoIdleResource.decrement()
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        EspressoIdleResource.decrement()
                        data.value = ApiResponse.error(
                            "Fail", MovieResponse(
                                arrayListOf(),
                                API.MESSAGE_FAIL
                            )
                        )
                    }
                })
                return data
            }

            override fun saveCallResult(data: MovieResponse) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data.results) {
                    val course = MovieEntity(
                        response.id,
                        response.title,
                        response.overview,
                        response.poster_path,
                        response.release_date,
                        response.vote_average,
                        response.isFavorite,
                    )
                    movieList.add(course)
                }

                movieDao.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getListMovieFavoritePaging(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(movieDao.getAllMovieFavorite(), config).build()
    }

    override fun saveFavoriteMovie(movieEntity: MovieEntity) {
        appExecutors.diskIO().execute { movieDao.updateMovieEntities(movieEntity) }
    }

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
                data.value = MovieDetailResponse(
                    arrayListOf(),
                    0,
                    "",
                    "",
                    0.0,
                    "",
                    "",
                    "",
                    0.0,
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