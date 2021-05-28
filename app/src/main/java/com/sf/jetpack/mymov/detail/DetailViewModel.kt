package com.sf.jetpack.mymov.detail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.data.Movie
import com.sf.jetpack.mymov.data.TvShowsData
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.DataCreditResponse
import com.sf.jetpack.mymov.network.response.MovieDetailResponse
import com.sf.jetpack.mymov.network.response.DataRecommendationsResponse
import com.sf.jetpack.mymov.network.response.TvDetailResponse
import com.sf.jetpack.mymov.utils.API
import com.sf.jetpack.mymov.utils.DummyData

class DetailViewModel(
    private val tvShowRepository: ITvRepository,
    private val movieRepository: IMovieRepository
) : ViewModel() {
    private var errorMessage = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    private lateinit var selectedId: String

    fun setSelectedId(selectedId: String) {
        this.selectedId = selectedId
    }

    fun getDetailMovie(): Movie {
        lateinit var movie: Movie
        val movies = DummyData.generateMovieListData()
        movies.forEach { data ->
            if (selectedId == data.id.toString()) {
                movie = data
            }
        }
        return movie
    }

    fun getDetailTvShow(): TvShowsData {
        lateinit var tvShow: TvShowsData
        val tvShows = DummyData.generateTvShowListData()
        tvShows.forEach { data ->
            if (selectedId == data.id.toString()) {
                tvShow = data
            }
        }
        return tvShow
    }

    fun getDetailMovieFromApi(
        movieId: String,
        lifecycleOwner: LifecycleOwner
    ): LiveData<MovieDetailResponse> {
        isLoading.value = true
        val movieData = MutableLiveData<MovieDetailResponse>()
        movieRepository.getDetailMovie(movieId).observe(lifecycleOwner, {
            isLoading.value = false
            if (it.message != API.MESSAGE_FAIL) {
                movieData.value = it
            } else {
                errorMessage.value = it.message
            }
        })
        return movieData
    }

    fun getMovieCredit(movieId: String): LiveData<DataCreditResponse> =
        movieRepository.getMovieCredit(movieId)

    fun getMovieRecommendations(movieId: String): LiveData<DataRecommendationsResponse> =
        movieRepository.getMovieRecommendations(movieId)

    fun getDetailTvFromApi(
        tvId: String,
        lifecycleOwner: LifecycleOwner
    ): LiveData<TvDetailResponse> {
        isLoading.value = true
        val tvShowData = MutableLiveData<TvDetailResponse>()
        tvShowRepository.getDetailTv(tvId).observe(lifecycleOwner, {
            isLoading.value = false
            if (it.message != API.MESSAGE_FAIL) {
                tvShowData.value = it
            } else {
                errorMessage.value = it.message
            }
        })
        return tvShowData
    }

    fun getTvShowCredit(tvId: String): LiveData<DataCreditResponse> =
        tvShowRepository.getTvShowCredit(tvId)

    fun getTvShowRecommendations(tvId: String): LiveData<DataRecommendationsResponse> =
        tvShowRepository.getTvShowRecommendations(tvId)
}