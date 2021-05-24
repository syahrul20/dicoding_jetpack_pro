package com.sf.jetpack.mymov.fragment.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sf.jetpack.mymov.adapter.MoviesAdapter
import com.sf.jetpack.mymov.databinding.FragmentMoviesBinding
import com.sf.jetpack.mymov.detail.DetailActivity
import com.sf.jetpack.mymov.network.response.MovieData
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), MoviesAdapter.IMovie {

    private val viewModel: MovieViewModel by viewModel()

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter

    companion object {
        fun newInstance() = MoviesFragment()
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

        setUpRecyclerView()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.getListMovieFromApi().observe(viewLifecycleOwner, { data ->
            moviesAdapter = MoviesAdapter(data.results, this)
            binding.rvMovie.adapter = moviesAdapter
        })
    }

    private fun setUpRecyclerView() {
        val data = viewModel.getListMovie()
    }

    override fun onMovieClicked(movie: MovieData) {
        Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra(Extra.ID, movie.id.toString())
            putExtra(Extra.TYPE, TYPE.MOVIE.name)
            startActivity(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}