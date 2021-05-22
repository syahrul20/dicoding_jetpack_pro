package com.sf.jetpack.mymov.detail

import com.sf.jetpack.mymov.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovieEntity = DummyData.generateMovieListData()[0]
    private val dummyTvShowEntity = DummyData.generateTvShowListData()[0]
    private val movieId = dummyMovieEntity.id
    private val tvShowId = dummyTvShowEntity.id


    @Before
    fun setUp(){
        viewModel = DetailMovieViewModel()
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
}