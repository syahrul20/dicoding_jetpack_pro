package com.sf.jetpack.mymov.data.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.db.MovieDao
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.state.Resource
import com.sf.jetpack.mymov.utils.AppExecutors
import com.sf.jetpack.mymov.utils.DummyData
import com.sf.jetpack.mymov.fragment.movie.MovieViewModel
import com.sf.jetpack.mymov.network.repository.MovieRepository
import com.sf.jetpack.mymov.utils.PagedListUtil
import com.sf.jetpack.mymov.utils.PagedTestDataSources
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.mock

@RunWith(MockitoJUnitRunner::class)
class MovieTest {

    private val movieDummy = DummyData.generateListMovieResponse()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var movieDataSource: MovieDataSource

    @Mock
    private lateinit var movieDao: MovieDao

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Mock
    private lateinit var fakeMovieRepository: FakeMovieRepository

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var moviePagingObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepository(movieDataSource, movieDao, appExecutors)
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun `get list movie paging from repository`() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(movieDao.getAllMovie()).thenReturn(dataSourceFactory)
        fakeMovieRepository.getListMoviePaging()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateListMovieResponse().results))
        verify(movieDao).getAllMovie()

        val expected = DummyData.generateListMovieResponse().results.size.toLong()
        val actual = movieEntities.data?.size?.toLong()
        assertNotNull(movieEntities.data)
        assertEquals(expected, actual)
    }

    @Test
    fun `get list movie paging from viewModel`() {
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        val movie = PagedTestDataSources.snapshot((movieDummy.results as? List<*>)!!)
        expected.value = Resource.success(movie) as Resource<PagedList<MovieEntity>>

        `when`(movieRepository.getListMoviePaging()).thenReturn(expected)
        viewModel.getListMoviePaging().observeForever(moviePagingObserver)
        verify(moviePagingObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getListMoviePaging().value
        assertNotNull(expectedValue)
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
    }
}