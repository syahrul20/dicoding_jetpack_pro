package com.sf.jetpack.mymov.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.sf.jetpack.mymov.adapter.ItemStateLoadingAdapter
import com.sf.jetpack.mymov.adapter.TvShowPagingAdapter
import com.sf.jetpack.mymov.adapter.TvShowsAdapter
import com.sf.jetpack.mymov.databinding.FragmentTvShowsBinding
import com.sf.jetpack.mymov.detail.DetailTvShowActivity
import com.sf.jetpack.mymov.network.response.TvResultList
import com.sf.jetpack.mymov.utils.Extra
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment(), TvShowsAdapter.ITvShow {

    private val viewModel: TvShowViewModel by viewModel()
    private val tvShowPagingAdapter by lazy {
        TvShowPagingAdapter()
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
        binding.rvTvShows.adapter = tvShowPagingAdapter.withLoadStateFooter(
            footer = ItemStateLoadingAdapter { tvShowPagingAdapter.retry() }
        )
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            viewModel.getListTvShowPaging().collectLatest {
                tvShowPagingAdapter.submitData(it)
            }
        }
    }

    override fun onTvShowClickListener(tvShow: TvResultList) {
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