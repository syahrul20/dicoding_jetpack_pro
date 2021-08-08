package com.sf.jetpack.mymov.fragment.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.*
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import com.sf.jetpack.mymov.network.response.MovieResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

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
    private lateinit var moviePagingRepository: IMoviePagingRepository

    @Mock
    private lateinit var roomRepository: IRoomRepository

    @Mock
    private lateinit var movieDataSource: MovieDataSource

    @Mock
    private lateinit var movieObserver: Observer<MovieResponse>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(roomRepository, moviePagingRepository)
    }

    @Test
    fun getListMoviePaging(): Unit = runBlocking {
        launch {
            val job = launch {
                val data = Pager(
                    config = PagingConfig(
                        pageSize = 10,
                        maxSize = 100,
                        enablePlaceholders = false
                    ),
                    pagingSourceFactory = { MoviePagingSource(movieDataSource) }
                ).flow
                `when`(moviePagingRepository.getListMoviePaging()).thenReturn(data)
            }
            delay(1000)
            val moviePaging = viewModel.getListMoviePaging().asLiveData().value
            verify(moviePagingRepository).getListMoviePaging()
//            assertEquals(mock, moviePaging)
//            assertNotNull(moviePaging)
            println(moviePaging)
            job.cancel()
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