package com.sf.jetpack.mymov.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.nhaarman.mockitokotlin2.verify
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import com.sf.jetpack.mymov.network.response.DataCreditResponse
import com.sf.jetpack.mymov.network.response.DataRecommendationsResponse
import com.sf.jetpack.mymov.network.response.MovieDetailResponse
import com.sf.jetpack.mymov.network.response.TvDetailResponse
import com.sf.jetpack.mymov.utils.DummyData
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val movieDummy = DummyData.generateMovieDetailData()
    private val tvDummy = DummyData.generateDetailTvResponse()
    private val _movieCreditDummy = DummyData.generateMovieCredit()
    private val _movieRecommendationDummy = DummyData.generateMovieRecommendations()
    private val _tvShowCreditDummy = DummyData.generateTvShowCredit()
    private val _tvShowRecommendationDummy = DummyData.generateTvShowRecommendation()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Mock
    private lateinit var tvShowRepository: ITvRepository

    @Mock
    private lateinit var roomRepository: IRoomRepository

    @Mock
    private lateinit var observerMovieDetail: Observer<MovieDetailResponse>

    @Mock
    private lateinit var observerTvShowDetail: Observer<TvDetailResponse>

    @Mock
    private lateinit var observerMovieDataCredit: Observer<DataCreditResponse>

    @Mock
    private lateinit var observerTvShowDataCredit: Observer<DataCreditResponse>

    @Mock
    private lateinit var observerMovieDataRecommendations: Observer<DataRecommendationsResponse>

    @Mock
    private lateinit var observerTvShowDataRecommendations: Observer<DataRecommendationsResponse>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository, tvShowRepository, roomRepository)
    }

    @Test
    fun getDetailMovieFromApi() {
        val movieDetailDataDummy = DummyData.generateMovieDetailData()
        val movieDetailData = MutableLiveData<MovieDetailResponse>()
        movieDetailData.value = movieDetailDataDummy

        `when`(movieRepository.getDetailMovie(movieDummy.id.toString())).thenReturn(movieDetailData)
        val movieDetailEntities =
            viewModel.getDetailMovieFromApi(movieDummy.id.toString()).value as MovieDetailResponse
        verify(movieRepository).getDetailMovie(movieDummy.id.toString())
        assertNotNull(movieDetailEntities)
        assertEquals(movieDummy.originalTitle, movieDetailEntities.originalTitle)
        assertEquals(movieDummy.releaseDate, movieDetailEntities.releaseDate)
        assertEquals(movieDummy.genres, movieDetailEntities.genres)
        assertEquals(movieDummy.voteAverage, movieDetailEntities.voteAverage)
        assertEquals(movieDummy.overview, movieDetailEntities.overview)

        viewModel.getDetailMovieFromApi(movieDummy.id.toString())
            .observeForever(observerMovieDetail)
        verify(observerMovieDetail).onChanged(movieDetailDataDummy)
    }

    @Test
    fun getMovieCredit() {
        val movieCreditDummy = DummyData.generateMovieCredit()
        val movieCreditData = MutableLiveData<DataCreditResponse>()
        movieCreditData.value = movieCreditDummy

        `when`(movieRepository.getMovieCredit(movieDummy.id.toString())).thenReturn(movieCreditData)
        val movieCreditEntities =
            viewModel.getMovieCredit(movieDummy.id.toString()).value as DataCreditResponse
        verify(movieRepository).getMovieCredit(movieDummy.id.toString())
        assertNotNull(movieCreditEntities)
        assertEquals(_movieCreditDummy.cast[0].creditId, movieCreditEntities.cast[0].creditId)
        assertEquals(_movieCreditDummy.cast[0].name, movieCreditEntities.cast[0].name)
        assertEquals(_movieCreditDummy.cast[0].character, movieCreditEntities.cast[0].character)
        assertEquals(_movieCreditDummy.crew[0].creditId, movieCreditEntities.crew[0].creditId)
        assertEquals(_movieCreditDummy.crew[0].name, movieCreditEntities.crew[0].name)
        assertEquals(_movieCreditDummy.crew[0].job, movieCreditEntities.crew[0].job)

        viewModel.getMovieCredit(movieDummy.id.toString()).observeForever(observerMovieDataCredit)
        verify(observerMovieDataCredit).onChanged(movieCreditDummy)
    }

    @Test
    fun getMovieRecommendation() {
        val movieRecommendations = DummyData.generateMovieRecommendations()
        val movieRecommendationData = MutableLiveData<DataRecommendationsResponse>()
        movieRecommendationData.value = movieRecommendations

        `when`(movieRepository.getMovieRecommendations(movieDummy.id.toString())).thenReturn(
            movieRecommendationData
        )
        val movieRecommendationEntities =
            viewModel.getMovieRecommendations(movieDummy.id.toString()).value as DataRecommendationsResponse
        verify(movieRepository).getMovieRecommendations(movieDummy.id.toString())
        assertNotNull(movieRecommendationEntities)
        assertEquals(
            _movieRecommendationDummy.results[0].id,
            movieRecommendationEntities.results[0].id
        )
        assertEquals(
            _movieRecommendationDummy.results[0].originalTitle,
            movieRecommendationEntities.results[0].originalTitle
        )
        assertEquals(
            _movieRecommendationDummy.results[0].overview,
            movieRecommendationEntities.results[0].overview
        )

        viewModel.getMovieRecommendations(movieDummy.id.toString())
            .observeForever(observerMovieDataRecommendations)
        verify(observerMovieDataRecommendations).onChanged(movieRecommendations)
    }

    @Test
    fun getTvDetailFromApi() {
        val tvShowDetailDataDummy = DummyData.generateDetailTvResponse()
        val tvShowDetailData = MutableLiveData<TvDetailResponse>()
        tvShowDetailData.value = tvShowDetailDataDummy

        `when`(tvShowRepository.getDetailTv(tvDummy.id.toString())).thenReturn(tvShowDetailData)
        val tvShowDetailEntities =
            viewModel.getDetailTvFromApi(tvDummy.id.toString()).value as TvDetailResponse
        verify(tvShowRepository).getDetailTv(tvDummy.id.toString())
        assertNotNull(tvShowDetailEntities)
        assertEquals(tvDummy.name, tvShowDetailEntities.name)
        assertEquals(tvDummy.overview, tvShowDetailEntities.overview)
        assertEquals(tvDummy.genres.size, tvShowDetailEntities.genres.size)
        assertEquals(tvDummy.firstAirDate, tvShowDetailEntities.firstAirDate)

        viewModel.getDetailTvFromApi(tvDummy.id.toString()).observeForever(observerTvShowDetail)
        verify(observerTvShowDetail).onChanged(tvShowDetailDataDummy)
    }

    @Test
    fun getTvShowCredit() {
        val tvShowCreditDummy = DummyData.generateTvShowCredit()
        val tvShowCreditData = MutableLiveData<DataCreditResponse>()
        tvShowCreditData.value = tvShowCreditDummy

        `when`(tvShowRepository.getTvShowCredit(tvDummy.id.toString())).thenReturn(tvShowCreditData)
        val tvShowCreditEntities =
            viewModel.getTvShowCredit(tvDummy.id.toString()).value as DataCreditResponse
        verify(tvShowRepository).getTvShowCredit(tvDummy.id.toString())
        assertNotNull(tvShowCreditEntities)
        assertEquals(_tvShowCreditDummy.cast[0].creditId, tvShowCreditEntities.cast[0].creditId)
        assertEquals(_tvShowCreditDummy.cast[0].name, tvShowCreditEntities.cast[0].name)
        assertEquals(_tvShowCreditDummy.cast[0].character, tvShowCreditEntities.cast[0].character)
        assertEquals(_tvShowCreditDummy.crew[0].creditId, tvShowCreditEntities.crew[0].creditId)
        assertEquals(_tvShowCreditDummy.crew[0].name, tvShowCreditEntities.crew[0].name)
        assertEquals(_tvShowCreditDummy.crew[0].job, tvShowCreditEntities.crew[0].job)

        viewModel.getTvShowCredit(tvDummy.id.toString()).observeForever(observerTvShowDataCredit)
        verify(observerTvShowDataCredit).onChanged(tvShowCreditDummy)
    }

    @Test
    fun getTvShowRecommendation() {
        val tvShowRecommendations = DummyData.generateTvShowRecommendation()
        val tvShowRecommendationData = MutableLiveData<DataRecommendationsResponse>()
        tvShowRecommendationData.value = tvShowRecommendations

        `when`(tvShowRepository.getTvShowRecommendations(tvDummy.id.toString())).thenReturn(
            tvShowRecommendationData
        )
        val tvShowRecommendationEntities =
            viewModel.getTvShowRecommendations(tvDummy.id.toString()).value as DataRecommendationsResponse
        verify(tvShowRepository).getTvShowRecommendations(tvDummy.id.toString())
        assertNotNull(tvShowRecommendationEntities)
        assertEquals(
            _tvShowRecommendationDummy.results[0].id,
            tvShowRecommendationEntities.results[0].id
        )
        assertEquals(
            _tvShowRecommendationDummy.results[0].originalName,
            tvShowRecommendationEntities.results[0].originalName
        )
        assertEquals(
            _tvShowRecommendationDummy.results[0].overview,
            tvShowRecommendationEntities.results[0].overview
        )

        viewModel.getTvShowRecommendations(tvDummy.id.toString())
            .observeForever(observerTvShowDataRecommendations)
        verify(observerTvShowDataRecommendations).onChanged(tvShowRecommendations)
    }
}