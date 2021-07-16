package com.sf.jetpack.mymov.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.datasource.MoviePagingSource
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.exp

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Mock
    private lateinit var moviePagingRepository: IMoviePagingRepository

    @Mock
    private lateinit var roomRepository: IRoomRepository

    @Test
    fun getListMoviePaging(): Unit = runBlocking {
        launch {
//            `when`(moviePagingRepository.getListMoviePaging()).thenReturn(tData)
//            val expected = DummyData.generateListMovieResponse()
//            val pagingData = mock(PagingData::class.java) as PagingData<ListData>
//            `when`(moviePagingRepository.getListMoviePaging()).thenReturn(flow { pagingData })
//            moviePagingRepository.getListMoviePaging()

        }
    }

//    @Test
//    fun getListMove() {
//        val movieList = DummyData.generateListMovieResponse()
//        val dummyListMovie = MutableLiveData<MovieResponse>()
//        dummyListMovie.value = movieList
//
//        `when`(movieRepository.getListMovie()).thenReturn(dummyListMovie)
//        val movieEntities = viewModel.getListMovieFromApi().value as MovieResponse
//        verify(movieRepository).getListMovie()
//        assertNotNull(movieEntities)
//        assertEquals(10, movieEntities.results.size)
//        assertEquals(movieList.results[0].title, movieEntities.results[0].title)
//        viewModel.getListMovieFromApi().observeForever(movieObserver)
//        verify(movieObserver).onChanged(movieList)
//    }
}