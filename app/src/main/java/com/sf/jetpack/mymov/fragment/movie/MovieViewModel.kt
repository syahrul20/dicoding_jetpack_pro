package com.sf.jetpack.mymov.fragment.movie

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.state.Resource


class MovieViewModel(
    private val movieRepository :IMovieRepository
) : ViewModel() {

    fun getListMoviePaging(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getListMoviePaging()

    fun getListMovieBookmarkPaging(): LiveData<PagedList<MovieEntity>> = movieRepository.getListMovieBookmarkPaging()

    fun saveBookmarkMovie(movie: MovieEntity) {
        movieRepository.saveBookmarkMovie(movie)
    }
}