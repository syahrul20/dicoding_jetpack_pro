package com.sf.jetpack.mymov.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sf.jetpack.mymov.adapter.MoviesBookmarkAdapter
import com.sf.jetpack.mymov.databinding.FragmentMoviesBookmarkBinding
import com.sf.jetpack.mymov.db.MovieEntity
import com.sf.jetpack.mymov.detail.DetailMovieActivity
import com.sf.jetpack.mymov.utils.Extra
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieBookmarkFragment : Fragment(), MoviesBookmarkAdapter.IMovie {

    private val viewModel: MovieViewModel by viewModel()

    private var _binding: FragmentMoviesBookmarkBinding? = null
    private val binding get() = _binding!!

    private val moviesBookmarkAdapter: MoviesBookmarkAdapter by lazy {
        MoviesBookmarkAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        initData()
    }

    private fun setUpView() {
        binding.rvMovieBookmark.adapter = moviesBookmarkAdapter
    }

    private fun initData() {
        setLoading(true)
        viewModel.getListMovieBookmarkPaging().observe(viewLifecycleOwner, { movieBookmarkList ->
            setLoading(false)
            binding.containerNoData.isVisible = movieBookmarkList.isEmpty()
            moviesBookmarkAdapter.submitList(movieBookmarkList)
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

    override fun onItemBookmarkClicked(movie: MovieEntity) {
        movie.isBookmark = if (movie.isBookmark == 1) 0 else 1
        viewModel.saveBookmarkMovie(movie)
        moviesBookmarkAdapter.notifyItemChanged(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
