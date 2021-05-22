package com.sf.jetpack.mymov.fragment.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sf.jetpack.mymov.adapter.MoviesAdapter
import com.sf.jetpack.mymov.data.Movie
import com.sf.jetpack.mymov.databinding.FragmentMoviesBinding
import com.sf.jetpack.mymov.detail.DetailActivity
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE

class MoviesFragment : Fragment(), MoviesAdapter.IMovie {

    private val viewModel: MovieViewModel by viewModels()

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
    }

    private fun setUpRecyclerView() {
        val data = viewModel.getListMovie()
        moviesAdapter = MoviesAdapter(data, requireContext(), this)
        binding.rvMovie.adapter = moviesAdapter
    }

    override fun onMovieClicked(movie: Movie) {
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