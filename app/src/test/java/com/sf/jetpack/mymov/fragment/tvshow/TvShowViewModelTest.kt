package com.sf.jetpack.mymov.fragment.tvshow

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getListTvShow() {
        val tvShows = viewModel.getListTvShow()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}