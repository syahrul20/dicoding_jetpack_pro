package com.sf.jetpack.mymov.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sf.jetpack.mymov.adapter.MoviesPagingAdapter
import com.sf.jetpack.mymov.adapter.ItemStateLoadingAdapter
import com.sf.jetpack.mymov.databinding.FragmentMoviesBinding
import com.sf.jetpack.mymov.db.AppDatabase
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.detail.DetailMovieActivity
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), MoviesPagingAdapter.IMovie {

    private val viewModel: MovieViewModel by viewModel()

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private var movieFavoriteList = ArrayList<FavoriteEntity>()
    private val moviesPagingAdapter: MoviesPagingAdapter by lazy {
        MoviesPagingAdapter(this, movieFavoriteList)
    }

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

        setUpView()
        setUpObserver()
    }

    private fun setUpView() {
        binding.rvMovie.adapter = moviesPagingAdapter
        binding.rvMovie.adapter = moviesPagingAdapter.withLoadStateFooter(
            footer = ItemStateLoadingAdapter { moviesPagingAdapter.retry() }
        )
    }

    private fun setUpObserver() {
        viewModel.getAllMovieFavorite()
        setLoading(true)
        lifecycleScope.launch {
            viewModel.listMovie.collectLatest {
                delay(500)
                setLoading(false)
                moviesPagingAdapter.submitData(it)
            }
        }

        viewModel.movieFavoriteData.observe(viewLifecycleOwner, { favoriteList ->
            movieFavoriteList.addAll(favoriteList)
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

    override fun onMovieClicked(movie: ListData) {
        Intent(requireActivity(), DetailMovieActivity::class.java).apply {
            putExtra(Extra.DATA, movie)
            startActivity(this)
        }
    }

    override fun onItemFavoriteClicked(movie: ListData) {
        movie.isFavorite = if (movie.isFavorite == 0) 1 else 0
        movie.type = TYPE.MOVIE.name
        val favoriteData = FavoriteEntity(
            movie.id,
            movie.title,
            movie.overview,
            movie.poster_path,
            movie.release_date,
            movie.vote_average,
            movie.isFavorite,
            movie.type!!
        )
        if (movie.isFavorite == 1) {
            viewModel.insertMovieFavorite(favoriteData)
        } else {
            viewModel.deleteMovieFavorite(favoriteData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}