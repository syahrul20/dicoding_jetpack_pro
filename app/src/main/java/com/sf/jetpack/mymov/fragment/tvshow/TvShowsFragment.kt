package com.sf.jetpack.mymov.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.sf.jetpack.mymov.adapter.TvShowPagingAdapter
import com.sf.jetpack.mymov.databinding.FragmentTvShowsBinding
import com.sf.jetpack.mymov.db.TvShowEntity
import com.sf.jetpack.mymov.detail.DetailTvShowActivity
import com.sf.jetpack.mymov.network.state.Status
import com.sf.jetpack.mymov.utils.Extra
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment(), TvShowPagingAdapter.ITvShow {

    private val viewModel: TvShowViewModel by viewModel()
    private val tvShowPagingAdapter by lazy {
        TvShowPagingAdapter(this)
    }

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

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
        setUpObserver()
    }

    private fun setUpRecyclerView() {
        binding.rvTvShows.adapter = tvShowPagingAdapter
    }

    private fun setUpObserver() {
        viewModel.getListTvShowPaging().observe(viewLifecycleOwner, { tvShow ->
            tvShow?.let {
                when(tvShow.status) {
                    Status.LOADING -> {
                        setLoading(true)
                    }
                    Status.SUCCESS -> {
                        setLoading(false)
                        tvShowPagingAdapter.submitList(tvShow.data)
                    }
                    Status.ERROR -> {
                        setLoading(false)
                    }
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) {
            progressLoading.isVisible = true
            rvTvShows.isVisible = false
        } else {
            progressLoading.isVisible = false
            rvTvShows.isVisible = true
        }
    }

    override fun onTvShowClickListener(tvShow: TvShowEntity) {
        Intent(requireActivity(), DetailTvShowActivity::class.java).apply {
            putExtra(Extra.DATA, tvShow)
            startActivity(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}