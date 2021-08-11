package com.sf.jetpack.mymov.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sf.jetpack.mymov.adapter.MoviesFavoriteAdapter
import com.sf.jetpack.mymov.databinding.FragmentMoviesFavoriteBinding
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.detail.DetailMovieActivity
import com.sf.jetpack.mymov.utils.Extra
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment(), MoviesFavoriteAdapter.IMovie {

    private val viewModel: MovieViewModel by viewModel()

    private var _binding: FragmentMoviesFavoriteBinding? = null
    private val binding get() = _binding!!

    private val moviesFavoriteAdapter: MoviesFavoriteAdapter by lazy {
        MoviesFavoriteAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        initData()
    }

    private fun setUpView() {
        binding.rvMovieBookmark.adapter = moviesFavoriteAdapter
    }

    private fun initData() {
        setLoading(true)
        viewModel.getListMovieFavoritePaging().observe(viewLifecycleOwner, { movieFavoriteList ->
            setLoading(false)
            binding.containerNoData.isVisible = movieFavoriteList.isEmpty()
            moviesFavoriteAdapter.submitList(movieFavoriteList)
        })
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) {
            progressLoading.isVisible = true
            rvMovieBookmark.isVisible = false
        } else {
            progressLoading.isVisible = false
            rvMovieBookmark.isVisible = true
        }
    }

    override fun onMovieClicked(movie: MovieEntity) {
        Intent(requireActivity(), DetailMovieActivity::class.java).apply {
            putExtra(Extra.DATA, movie)
            startActivity(this)
        }
    }

    override fun onItemFavoriteClicked(movie: MovieEntity) {
        movie.isFavorite = if (movie.isFavorite == 1) 0 else 1
        viewModel.saveFavoriteMovie(movie)
        moviesFavoriteAdapter.notifyItemChanged(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
