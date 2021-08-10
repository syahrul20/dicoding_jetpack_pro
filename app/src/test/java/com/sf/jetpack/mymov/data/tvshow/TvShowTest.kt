package com.sf.jetpack.mymov.data.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.db.TVShowDao
import com.sf.jetpack.mymov.db.TvShowEntity
import com.sf.jetpack.mymov.network.state.Resource
import com.sf.jetpack.mymov.utils.AppExecutors
import com.sf.jetpack.mymov.utils.DummyData
import com.sf.jetpack.mymov.fragment.tvshow.TvShowViewModel
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.repository.TvRepository
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
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class TvShowTest {

    private val tvShowDummy = DummyData.generateListTvShowResponse()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowViewModel

    @Mock
    private lateinit var tvShowDataSource: TvDataSource

    @Mock
    private lateinit var tvShowDao: TVShowDao

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Mock
    private lateinit var fakeTvShowRepository: FakeTvShowRepository

    @Mock
    private lateinit var tvRepository: TvRepository

    @Mock
    private lateinit var tvShowPagingObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Before
    fun setUp() {
        fakeTvShowRepository = FakeTvShowRepository(tvShowDataSource, tvShowDao, appExecutors)
        viewModel = TvShowViewModel(tvRepository)
    }

    @Test
    fun `get list tvshow paging from repository`() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(tvShowDao.getAllTvShow()).thenReturn(dataSourceFactory)
        fakeTvShowRepository.getListTvShowPaging()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateListMovieResponse().results))
        verify(tvShowDao).getAllTvShow()

        val expected = DummyData.generateListMovieResponse().results.size.toLong()
        val actual = movieEntities.data?.size?.toLong()
        assertNotNull(movieEntities.data)
        assertEquals(expected, actual)
    }

    @Test
    fun `get list tvshow paging from viewModel`() {
        val expected = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        val movie = PagedTestDataSources.snapshot((tvShowDummy.results as? List<TvShowEntity>)!!)
        expected.value = Resource.success(movie)

        `when`(tvRepository.getListTvShowPaging()).thenReturn(expected)
        viewModel.getListTvShowPaging().observeForever(tvShowPagingObserver)
        verify(tvShowPagingObserver).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getListTvShowPaging().value
        assertNotNull(expectedValue)
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
    }
}