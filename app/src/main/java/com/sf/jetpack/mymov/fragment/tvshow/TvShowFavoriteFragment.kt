package com.sf.jetpack.mymov.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.sf.jetpack.mymov.adapter.ItemStateLoadingAdapter
import com.sf.jetpack.mymov.adapter.TvShowsFavoriteAdapter
import com.sf.jetpack.mymov.databinding.FragmentTvShowFavoriteBinding
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.detail.DetailTvShowActivity
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment : Fragment(), TvShowsFavoriteAdapter.ITvShow {

    private val viewModel: TvShowViewModel by viewModel()
    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding!!
    private var tvShowFavoriteList = ArrayList<FavoriteEntity>()
    private val tvShowFavoriteAdapter: TvShowsFavoriteAdapter by lazy {
        TvShowsFavoriteAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setupObserver()
    }

    private fun setUpView() {
        binding.rvTvShows.adapter = tvShowFavoriteAdapter
        binding.rvTvShows.adapter = tvShowFavoriteAdapter.withLoadStateFooter(
            footer = ItemStateLoadingAdapter { tvShowFavoriteAdapter.retry() }
        )
        tvShowFavoriteAdapter.addLoadStateListener { loadState ->
            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && tvShowFavoriteAdapter.itemCount == 0
            val isLoading = loadState.source.refresh is LoadState.Loading
            binding.containerNoData.isVisible = isListEmpty
            setLoading(isLoading)
        }
    }

    private fun setupObserver() {
        viewModel.getAllFavorite()
        lifecycleScope.launch {
            viewModel.getListFavoriteTvShow().collectLatest {
                tvShowFavoriteAdapter.submitData(it)
            }
        }
        viewModel.tvShowFavoriteData.observe(viewLifecycleOwner, { favoriteList ->
            val dataFiltered = favoriteList.filter { it.type == TYPE.TV_SHOW.name }
            tvShowFavoriteList.addAll(dataFiltered)
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

    override fun onTvShowClicked(tvShow: FavoriteEntity) {
        Intent(requireActivity(), DetailTvShowActivity::class.java).apply {
            putExtra(Extra.DATA, tvShow)
            startActivity(this)
        }
    }

    override fun onItemFavoriteClicked(tvShow: FavoriteEntity) {
        tvShow.isFavorite = if (tvShow.isFavorite == 1) 0 else 1
        viewModel.deleteMovieFavorite(tvShow)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
