package com.sf.jetpack.mymov.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.db.TvShowEntity
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

    fun getDetailMovieFromApi(
        movieId: String
    ): LiveData<MovieDetailResponse> = movieRepository.getDetailMovie(movieId)

    fun getMovieCredit(movieId: String): LiveData<DataCreditResponse> =
        movieRepository.getMovieCredit(movieId)

    fun getMovieRecommendations(movieId: String): LiveData<DataRecommendationsResponse> =
        movieRepository.getMovieRecommendations(movieId)

    fun saveBookmarkMovie(movie: MovieEntity) {
        movieRepository.saveBookmarkMovie(movie)
    }

    fun saveBookmarkTvShow(tvShowEntity: TvShowEntity) {
        tvShowRepository.saveBookmarkTvShow(tvShowEntity)
    }

    fun getDetailTvFromApi(tvId: String): LiveData<TvDetailResponse> =
        tvShowRepository.getDetailTv(tvId)

    fun getTvShowCredit(tvId: String): LiveData<DataCreditResponse> =
        tvShowRepository.getTvShowCredit(tvId)

    fun getTvShowRecommendations(tvId: String): LiveData<DataRecommendationsResponse> =
        tvShowRepository.getTvShowRecommendations(tvId)
}