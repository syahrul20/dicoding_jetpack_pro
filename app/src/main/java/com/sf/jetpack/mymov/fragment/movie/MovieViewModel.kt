package com.sf.jetpack.mymov.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.data.Movie
import com.sf.jetpack.mymov.network.repository.MovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.response.MovieCreditResponse
import com.sf.jetpack.mymov.network.response.MovieResponse
import com.sf.jetpack.mymov.utils.DummyData


class MovieViewModel(private val movieRepository: IMovieRepository) : ViewModel() {

    fun getListMovie(): List<Movie> {
        return DummyData.generateMovieListData()
    }

    fun getListMovieFromApi(): LiveData<MovieResponse> = movieRepository.getListMovie()
}