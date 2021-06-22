package com.sf.jetpack.mymov.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sf.jetpack.mymov.adapter.MoviesAdapter
import com.sf.jetpack.mymov.adapter.MoviesPagingAdapter
import com.sf.jetpack.mymov.adapter.ItemStateLoadingAdapter
import com.sf.jetpack.mymov.databinding.FragmentMoviesBinding
import com.sf.jetpack.mymov.detail.DetailMovieActivity
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.Extra
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), MoviesAdapter.IMovie {

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
        moviesPagingAdapter = MoviesPagingAdapter()
        binding.rvMovie.adapter = moviesPagingAdapter
        binding.rvMovie.adapter = moviesPagingAdapter.withLoadStateFooter(
            footer = ItemStateLoadingAdapter { moviesPagingAdapter.retry() }
        )
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            viewModel.listMovie.collect {
                moviesPagingAdapter.submitData(it)
            }
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