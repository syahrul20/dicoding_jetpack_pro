package com.sf.jetpack.mymov.fragment.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.MovieResponse
import com.sf.jetpack.mymov.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieResponse>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getListMove() {
        val movieList = DummyData.generateListMovieResponse()
        val dummyListMovie = MutableLiveData<MovieResponse>()
        dummyListMovie.value = movieList

        `when`(movieRepository.getListMovie()).thenReturn(dummyListMovie)
        val movieEntities = viewModel.getListMovieFromApi().value as MovieResponse
        verify(movieRepository).getListMovie()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.results.size)
        assertEquals(movieList.results[0].title, movieEntities.results[0].title)
        viewModel.getListMovieFromApi().observeForever(movieObserver)
        verify(movieObserver).onChanged(movieList)
    }
}