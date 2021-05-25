package com.sf.jetpack.mymov.detail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.data.Movie
import com.sf.jetpack.mymov.data.TvShowsData
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.MovieCreditResponse
import com.sf.jetpack.mymov.network.response.MovieDetailResponse
import com.sf.jetpack.mymov.network.response.MovieRecommendationResponse
import com.sf.jetpack.mymov.network.response.TvDetailResponse
import com.sf.jetpack.mymov.utils.API
import com.sf.jetpack.mymov.utils.DummyData

class DetailViewModel(
    private val movieRepository: IMovieRepository,
    private val tvShowRepository: ITvRepository
) : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var tvShowData = MutableLiveData<TvDetailResponse>()
    var errorMessage = MutableLiveData<String>()

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

    fun getDetailMovieFromApi(movieId: String, lifecycleOwner: LifecycleOwner): LiveData<MovieDetailResponse> {
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

    fun getMovieCredit(movieId: String): LiveData<MovieCreditResponse> = movieRepository.getMovieCredit(movieId)
    fun getMovieRecommendations(movieId: String): LiveData<MovieRecommendationResponse> = movieRepository.getMovieRecommendations(movieId)

    fun getDetailTvFromApi(tvId: String, lifecycleOwner: LifecycleOwner) {
        isLoading.value = true
        tvShowRepository.getDetailTv(tvId).observe(lifecycleOwner, {
            isLoading.value = false
            if (it.message != API.MESSAGE_FAIL) {
                tvShowData.value = it
            } else {
                errorMessage.value = it.message
            }
        })
    }
}