package com.sf.jetpack.mymov.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sf.jetpack.mymov.adapter.MoviesFavoriteAdapter
import com.sf.jetpack.mymov.databinding.FragmentMoviesFavoriteBinding
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.detail.DetailMovieActivity
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment(), MoviesFavoriteAdapter.IMovie {

    private val viewModel: MovieViewModel by viewModel()

    private var _binding: FragmentMoviesFavoriteBinding? = null
    private val binding get() = _binding!!
//    private var movieFavoriteList = ArrayList<FavoriteEntity>()
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
        binding.rvMovie.adapter = moviesFavoriteAdapter
    }

    private fun initData() {
//        lifecycleScope.launch {
//            viewModel.listMovieFavorite().collectLatest {
//                moviesFavoriteAdapter.submitData(it)
//            }
//        }
//
//        viewModel.movieFavoriteData.observe(viewLifecycleOwner, { favoriteList ->
//            val dataFiltered = favoriteList.filter { it.type == TYPE.MOVIE.name }
//            movieFavoriteList.addAll(dataFiltered)
//        })
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

    override fun onMovieClicked(movie: MovieEntity) {
        Intent(requireActivity(), DetailMovieActivity::class.java).apply {
            putExtra(Extra.DATA, movie)
            startActivity(this)
        }
    }

    override fun onItemFavoriteClicked(movie: MovieEntity) {
        movie.isFavorite = if (movie.isFavorite == 1) 0 else 1
//        viewModel.deleteMovieFavorite(movie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
