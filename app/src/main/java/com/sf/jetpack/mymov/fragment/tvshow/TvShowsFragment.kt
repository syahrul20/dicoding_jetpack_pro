package com.sf.jetpack.mymov.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sf.jetpack.mymov.adapter.TvShowsAdapter
import com.sf.jetpack.mymov.data.TvShowsData
import com.sf.jetpack.mymov.databinding.FragmentTvShowsBinding
import com.sf.jetpack.mymov.detail.DetailActivity
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE

class TvShowsFragment : Fragment(), TvShowsAdapter.IMovie {

    private val viewModel: TvShowViewModel by viewModels()

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvShowAdapter: TvShowsAdapter

    companion object {
        fun newInstance() = TvShowsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val data = viewModel.getListTvShow()
        tvShowAdapter = TvShowsAdapter(data, requireContext(), this)
        binding.rvTvShows.adapter = tvShowAdapter
    }

    override fun onMovieClicked(tvShow: TvShowsData) {
        Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra(Extra.ID, tvShow.id.toString())
            putExtra(Extra.TYPE, TYPE.TV_SHOW.name)
            startActivity(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}