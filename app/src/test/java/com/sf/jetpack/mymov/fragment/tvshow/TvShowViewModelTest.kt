package com.sf.jetpack.mymov.fragment.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: ITvRepository

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getListTvShow() {
        val tvShows = viewModel.getListTvShow()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}