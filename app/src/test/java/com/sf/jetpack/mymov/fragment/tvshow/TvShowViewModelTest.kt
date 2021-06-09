package com.sf.jetpack.mymov.fragment.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.TvResponse
import com.sf.jetpack.mymov.utils.DummyData
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: ITvRepository

    @Mock
    private lateinit var tvShowObserver: Observer<TvResponse>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvShowRepository)
    }
    @Test
    fun getListTvShow() {
        val tvShowList = DummyData.generateListTvShowResponse()
        val dummyListTvShow = MutableLiveData<TvResponse>()
        dummyListTvShow.value = tvShowList

        Mockito.`when`(tvShowRepository.getListTvOnTheAir()).thenReturn(dummyListTvShow)
        val tvShowEntities = viewModel.getListTvShowFromApi().value as TvResponse
        verify(tvShowRepository).getListTvOnTheAir()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.results.size)
        assertEquals(tvShowList.results[0].name, tvShowEntities.results[0].name)
        viewModel.getListTvShowFromApi().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShowList)
    }
}