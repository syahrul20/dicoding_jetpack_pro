package com.sf.jetpack.mymov.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sf.jetpack.mymov.adapter.TvShowsFavoriteAdapter
import com.sf.jetpack.mymov.databinding.FragmentTvShowFavoriteBinding
import com.sf.jetpack.mymov.db.TvShowEntity
import com.sf.jetpack.mymov.detail.DetailTvShowActivity
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment : Fragment(), TvShowsFavoriteAdapter.ITvShow {

    private val viewModel: TvShowViewModel by viewModel()
    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding!!
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
        binding.rvTvShowsBookmark.adapter = tvShowFavoriteAdapter
    }

    private fun setupObserver() {
        setLoading(true)
        viewModel.getListTvShowFavoritePaging().observe(viewLifecycleOwner, { tvShowFavoriteList ->
            setLoading(false)
            binding.containerNoData.isVisible = tvShowFavoriteList.isEmpty()
            tvShowFavoriteAdapter.submitList(tvShowFavoriteList)
        })
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) {
            progressLoading.isVisible = true
            rvTvShowsBookmark.isVisible = false
        } else {
            progressLoading.isVisible = false
            rvTvShowsBookmark.isVisible = true
        }
    }

    override fun onTvShowClicked(tvShow: TvShowEntity) {
        Intent(requireActivity(), DetailTvShowActivity::class.java).apply {
            putExtra(Extra.DATA, tvShow)
            startActivity(this)
        }
    }

    override fun onItemFavoriteClicked(tvShow: TvShowEntity) {
        tvShow.isFavorite = if (tvShow.isFavorite == 1) 0 else 1
        viewModel.saveFavoriteTvShow(tvShow)
        tvShowFavoriteAdapter.notifyItemChanged(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
