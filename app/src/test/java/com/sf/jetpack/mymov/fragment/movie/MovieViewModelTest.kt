package com.sf.jetpack.mymov.fragment.movie

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getListMovie() {
        val movies = viewModel.getListMovie()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}