package com.sf.jetpack.mymov.fragment.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getListMovie() {
        val movies = viewModel.getListMovie()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}