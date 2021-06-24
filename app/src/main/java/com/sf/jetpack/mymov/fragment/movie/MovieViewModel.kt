package com.sf.jetpack.mymov.fragment.movie

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.response.MovieResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


class MovieViewModel(
    private val movieRepository: IMovieRepository,
    moviePagingRepository : IMoviePagingRepository
) : ViewModel() {
    val listMovie = moviePagingRepository.getListMoviePaging().cachedIn(viewModelScope)
    var isLoading = MutableLiveData<Boolean>()

    fun getListMovieFromApi(): LiveData<MovieResponse> = movieRepository.getListMovie()
}