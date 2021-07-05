package com.sf.jetpack.mymov.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sf.jetpack.mymov.adapter.MoviesFavoriteAdapter
import com.sf.jetpack.mymov.databinding.FragmentMoviesBinding
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.detail.DetailMovieActivity
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment(), MoviesFavoriteAdapter.IMovie {

    private val viewModel: MovieViewModel by viewModel()

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesFavoriteAdapter: MoviesFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        setLoading(true)
        viewModel.getAllMovieFavorite()
        viewModel.movieFavoriteData.observe(viewLifecycleOwner, {
            setLoading(false)
            val movieFavoriteList = it.filter { item -> item.type == TYPE.MOVIE.name }
            moviesFavoriteAdapter = MoviesFavoriteAdapter(movieFavoriteList, this)
            binding.rvMovie.adapter = moviesFavoriteAdapter
        })
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) {
            progressLoading.isVisible = true
            rvMovie.isVisible = false
        } else {
            progressLoading.isVisible = false
            rvMovie.isVisible = true
        }
    }

    override fun onMovieClicked(movie: FavoriteEntity) {
        Intent(requireActivity(), DetailMovieActivity::class.java).apply {
            putExtra(Extra.DATA, movie)
            startActivity(this)
        }
    }

    override fun onItemFavoriteClicked(movie: FavoriteEntity) {
        movie.isFavorite = if (movie.isFavorite == 1) 0 else 1
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            initData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
