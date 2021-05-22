package com.sf.jetpack.mymov.detail

import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.data.Movie
import com.sf.jetpack.mymov.data.TvShowsData
import com.sf.jetpack.mymov.utils.DummyData

class DetailMovieViewModel : ViewModel() {

    private lateinit var selectedId: String

    fun setSelectedId(selectedId: String) {
        this.selectedId = selectedId
    }

    fun getDetailMovie(): Movie {
        lateinit var movie: Movie
        val movies = DummyData.generateMovieListData()
        movies.forEach { data ->
            if (selectedId == data.id.toString()) {
                movie = data
            }
        }
        return movie
    }

    fun getDetailTvShow(): TvShowsData {
        lateinit var tvShow: TvShowsData
        val tvShows = DummyData.generateTvShowListData()
        tvShows.forEach { data ->
            if (selectedId == data.id.toString()) {
                tvShow = data
            }
        }
        return tvShow
    }
}