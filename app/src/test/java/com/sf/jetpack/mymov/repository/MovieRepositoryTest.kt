package com.sf.jetpack.mymov.fragment.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.network.datasource.MoviePagingSource
import com.sf.jetpack.mymov.network.repository.repocontract.IMoviePagingRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.DummyData
import com.sf.jetpack.mymov.utils.FakeApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Assert.assertNotNull
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.internal.verification.Times
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val fakeMovies = DummyData.generateListMovieResponse().results
    private val fakeApi = FakeApi()

    @Mock
    private lateinit var moviePagingRepository: IMoviePagingRepository

    @Mock
    private lateinit var roomRepository: IRoomRepository

    @Before
    fun setUp() {
        viewModel = MovieViewModel(roomRepository, moviePagingRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get movies from repository`(): Unit = runBlocking {
        val moviePagingSource = MoviePagingSource(fakeApi)
        val fakePagingData = Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { moviePagingSource }
        ).flow

        val expectedPagingData = PagingSource.LoadResult.Page(
            data = fakeMovies,
            prevKey = null,
            nextKey = (fakeMovies.size / 10) + 1
        )
        val actualPagingData = moviePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        `when`(moviePagingRepository.getListMoviePaging()).thenReturn(fakePagingData)
        val moviesEntities = viewModel.getListMoviePaging()
        verify(moviePagingRepository).getListMoviePaging()
        assertNotNull(moviesEntities)
        assertEquals(
            expected = expectedPagingData,
            actual = actualPagingData,
        )
    }
}