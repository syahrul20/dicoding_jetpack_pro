package com.sf.jetpack.mymov.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.data.movie.FakeMovieRepository
import com.sf.jetpack.mymov.data.tvshow.FakeTvShowRepository
import com.sf.jetpack.mymov.db.MovieDao
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.db.TVShowDao
import com.sf.jetpack.mymov.db.TvShowEntity
import com.sf.jetpack.mymov.network.state.Resource
import com.sf.jetpack.mymov.utils.DummyData
import com.sf.jetpack.mymov.fragment.movie.MovieViewModel
import com.sf.jetpack.mymov.fragment.tvshow.TvShowViewModel
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.repository.MovieRepository
import com.sf.jetpack.mymov.network.repository.TvRepository
import com.sf.jetpack.mymov.utils.AppExecutors
import com.sf.jetpack.mymov.utils.PagedListUtil
import com.sf.jetpack.mymov.utils.PagedTestDataSources
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.mock

@RunWith(MockitoJUnitRunner::class)
class BookmarkTest {

    private val movieDummy = DummyData.generateListMovieResponse()
    private val tvShowDummy = DummyData.generateListTvShowResponse()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var tvShowViewModel: TvShowViewModel

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Mock
    private lateinit var movieDao: MovieDao

    @Mock
    private lateinit var movieDataSource: MovieDataSource

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var fakeMovieRepository: FakeMovieRepository

    @Mock
    private lateinit var movieBookmarkObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvShowDataSource: TvDataSource

    @Mock
    private lateinit var tvShowDao: TVShowDao

    @Mock
    private lateinit var fakeTvShowRepository: FakeTvShowRepository

    @Mock
    private lateinit var tvRepository: TvRepository

    @Mock
    private lateinit var tvShowBookmarkObserver: Observer<PagedList<TvShowEntity>>

    @Before
    fun setUp() {
        fakeMovieRepository = FakeMovieRepository(movieDataSource, movieDao, appExecutors)
        fakeTvShowRepository = FakeTvShowRepository(tvShowDataSource, tvShowDao, appExecutors)
        movieViewModel = MovieViewModel(movieRepository)
        tvShowViewModel = TvShowViewModel(tvRepository)
    }

    @Test
    fun `get list movie bookmark from repository`() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(movieDao.getAllMovieBookmark()).thenReturn(dataSourceFactory)
        fakeMovieRepository.getListMovieBookmarkPaging()

        val movieBookmarkEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateListMovieResponse().results))
        verify(movieDao).getAllMovieBookmark()

        val expected = DummyData.generateListMovieResponse().results.size.toLong()
        val actual = movieBookmarkEntities.data?.size?.toLong()
        assertNotNull(movieBookmarkEntities.data)
        assertEquals(expected, actual)
    }

    @Test
    fun `get list movie bookmark from viewModel`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        val movie = PagedTestDataSources.snapshot((movieDummy.results as? List<MovieEntity>)!!)
        expected.value = movie

        `when`(movieRepository.getListMovieBookmarkPaging()).thenReturn(expected)
        movieViewModel.getListMovieBookmarkPaging().observeForever(movieBookmarkObserver)
        verify(movieBookmarkObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = movieViewModel.getListMovieBookmarkPaging().value
        assertNotNull(expectedValue)
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `get list movie bookmark success but empty data`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        val movie = PagedTestDataSources.snapshot(listOf<MovieEntity>())
        expected.value = movie

        `when`(movieRepository.getListMovieBookmarkPaging()).thenReturn(expected)
        movieViewModel.getListMovieBookmarkPaging().observeForever(movieBookmarkObserver)
        verify(movieBookmarkObserver).onChanged(expected.value)

        val actualValue = movieViewModel.getListMovieBookmarkPaging().value?.size
        assertTrue("size of the data should be 0, but actual is $actualValue", actualValue == 0)
    }


    @Test
    fun `get list tv show bookmark from repository`() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(tvShowDao.getAllTvShowBookmark()).thenReturn(dataSourceFactory)
        fakeTvShowRepository.getListTvShowBookmarkPaging()

        val tvShowBookmarkEntities =
            Resource.success(PagedListUtil.mockPagedList(tvShowDummy.results))
        verify(tvShowDao).getAllTvShowBookmark()

        val expected = tvShowDummy.results.size.toLong()
        val actual = tvShowBookmarkEntities.data?.size?.toLong()
        assertNotNull(tvShowBookmarkEntities.data)
        assertEquals(expected, actual)
    }

    @Test
    fun `get list tv show bookmark from viewModel`() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        val movie = PagedTestDataSources.snapshot((tvShowDummy.results as? List<TvShowEntity>)!!)
        expected.value = movie

        `when`(tvRepository.getListTvShowBookmarkPaging()).thenReturn(expected)
        tvShowViewModel.getListTvShowBookmarkPaging().observeForever(tvShowBookmarkObserver)
        verify(tvShowBookmarkObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = tvShowViewModel.getListTvShowBookmarkPaging().value
        assertNotNull(expectedValue)
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `get list tv show bookmark success but empty data`() {
        val expected = MutableLiveData<PagedList<TvShowEntity>>()
        val tvShow = PagedTestDataSources.snapshot(listOf<TvShowEntity>())
        expected.value = tvShow

        `when`(tvRepository.getListTvShowBookmarkPaging()).thenReturn(expected)
        tvShowViewModel.getListTvShowBookmarkPaging().observeForever(tvShowBookmarkObserver)
        verify(tvShowBookmarkObserver).onChanged(expected.value)

        val actualValue = tvShowViewModel.getListTvShowBookmarkPaging().value?.size
        assertTrue("size of the data should be 0, but actual is $actualValue", actualValue == 0)
    }
}