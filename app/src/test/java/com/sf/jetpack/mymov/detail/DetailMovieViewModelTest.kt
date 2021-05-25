package com.sf.jetpack.mymov.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.MovieDetailResponse
import com.sf.jetpack.mymov.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovieEntity = DummyData.generateMovieListData()[0]
    private val dummyTvShowEntity = DummyData.generateTvShowListData()[0]
    private val movieId = dummyMovieEntity.id
    private val movieDetailId = DummyData.generateMovieDetailData().id
    private val tvShowId = dummyTvShowEntity.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Mock
    private lateinit var tvShowRepository: ITvRepository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository, tvShowRepository)
        viewModel.setSelectedId(movieId.toString())
    }

    @Test
    fun getDetailMovie() {
        viewModel.setSelectedId(movieId.toString())
        val movieEntity = viewModel.getDetailMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovieEntity.id, movieEntity.id)
        assertEquals(dummyMovieEntity.name, movieEntity.name)
        assertEquals(dummyMovieEntity.image, movieEntity.image)
        assertEquals(dummyMovieEntity.description, movieEntity.description)
        assertEquals(dummyMovieEntity.linkTrailer, movieEntity.linkTrailer)
        assertEquals(dummyMovieEntity.director, movieEntity.director)
    }

    @Test
    fun getDetailTvShow() {
        viewModel.setSelectedId(tvShowId.toString())
        val tvShowEntity = viewModel.getDetailTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShowEntity.id, tvShowEntity.id)
        assertEquals(dummyTvShowEntity.name, tvShowEntity.name)
        assertEquals(dummyTvShowEntity.image, tvShowEntity.image)
        assertEquals(dummyTvShowEntity.description, tvShowEntity.description)
        assertEquals(dummyTvShowEntity.linkTrailer, tvShowEntity.linkTrailer)
        assertEquals(dummyTvShowEntity.creator, tvShowEntity.creator)
    }


    @Test
    fun getDetailMovieFromApi() {
        val movieDetailDataDummy = DummyData.generateMovieDetailData()
        val movieDetailData = MutableLiveData<MovieDetailResponse>()
        movieDetailData.value = movieDetailDataDummy

        val lifecycleOwner: LifecycleOwner = mock(LifecycleOwner::class.java)
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.currentState = Lifecycle.State.RESUMED
        `when`(lifecycleOwner.lifecycle).thenReturn(lifecycle)
        `when`(movieRepository.getDetailMovie(movieDetailId.toString())).thenReturn(movieDetailData)
        val movieDetailEntities = viewModel.getDetailMovieFromApi(movieDetailId.toString(), lifecycleOwner).value
        verify(movieRepository).getDetailMovie(movieDetailId.toString())
        assertNotNull(movieDetailEntities)
    }
}