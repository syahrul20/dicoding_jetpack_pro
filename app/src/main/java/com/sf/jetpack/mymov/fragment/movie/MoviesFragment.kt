package com.sf.jetpack.mymov.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.sf.jetpack.mymov.adapter.MoviesPagingAdapter
import com.sf.jetpack.mymov.adapter.ItemStateLoadingAdapter
import com.sf.jetpack.mymov.databinding.FragmentMoviesBinding
import com.sf.jetpack.mymov.detail.DetailMovieActivity
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.Extra
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), MoviesPagingAdapter.IMovie {

    private val viewModel: MovieViewModel by viewModel()

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesPagingAdapter: MoviesPagingAdapter

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
        moviesPagingAdapter = MoviesPagingAdapter(this)
        binding.rvMovie.adapter = moviesPagingAdapter
        binding.rvMovie.adapter = moviesPagingAdapter.withLoadStateFooter(
            footer = ItemStateLoadingAdapter { moviesPagingAdapter.retry() }
        )
        moviesPagingAdapter.addLoadStateListener { loadState ->
            val isLoading = loadState.source.refresh is LoadState.Loading
            setLoading(isLoading)
        }
    }

    private fun setUpObserver() {
        viewModel.getAllMovieFavorite()
        lifecycleScope.launch {
            viewModel.getListMoviePaging().collectLatest {
                moviesPagingAdapter.submitData(it)
            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}