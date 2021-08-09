package com.sf.jetpack.mymov.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi
 */

class DetailViewModel(
    private val movieRepository: IMovieRepository,
    private val tvShowRepository: ITvRepository,
) : ViewModel() {
    var isLoading = MutableLiveData(true)
    val favoriteData = MutableLiveData<List<MovieEntity>>()

    fun getDetailMovieFromApi(
        movieId: String
    ): LiveData<MovieDetailResponse> = movieRepository.getDetailMovie(movieId)

    fun getMovieCredit(movieId: String): LiveData<DataCreditResponse> =
        movieRepository.getMovieCredit(movieId)

    fun getMovieRecommendations(movieId: String): LiveData<DataRecommendationsResponse> =
        movieRepository.getMovieRecommendations(movieId)

    fun saveFavoriteMovie(movie: MovieEntity) {
        movieRepository.saveFavoriteMovie(movie)
    }

    fun getDetailTvFromApi(tvId: String): LiveData<TvDetailResponse> = tvShowRepository.getDetailTv(tvId)

    fun getTvShowCredit(tvId: String): LiveData<DataCreditResponse> =
        tvShowRepository.getTvShowCredit(tvId)

    fun getTvShowRecommendations(tvId: String): LiveData<DataRecommendationsResponse> =
        tvShowRepository.getTvShowRecommendations(tvId)
}