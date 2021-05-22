package com.sf.jetpack.mymov.fragment.movie

import androidx.lifecycle.ViewModel
import com.sf.jetpack.mymov.data.Movie
import com.sf.jetpack.mymov.utils.DummyData


class MovieViewModel: ViewModel() {

    fun getListMovie(): List<Movie> {
        return DummyData.generateMovieListData()
    }
}