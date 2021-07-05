package com.sf.jetpack.mymov.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.sf.jetpack.mymov.adapter.ItemStateLoadingAdapter
import com.sf.jetpack.mymov.adapter.TvShowPagingAdapter
import com.sf.jetpack.mymov.databinding.FragmentTvShowsBinding
import com.sf.jetpack.mymov.db.AppDatabase
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.detail.DetailTvShowActivity
import com.sf.jetpack.mymov.network.response.TvResultList
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
        binding.rvTvShows.adapter = tvShowPagingAdapter.withLoadStateFooter(
            footer = ItemStateLoadingAdapter { tvShowPagingAdapter.retry() }
        )
    }

    private fun setUpObserver() {
        setLoading(true)
        lifecycleScope.launch {
            viewModel.getListTvShowPaging().collectLatest {
                delay(500)
                setLoading(false)
                tvShowPagingAdapter.submitData(it)
            }
        }
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

    override fun onTvShowClickListener(tvShow: TvResultList) {
        Intent(requireActivity(), DetailTvShowActivity::class.java).apply {
            putExtra(Extra.DATA, tvShow)
            startActivity(this)
        }
    }

    override fun onItemFavoriteClicked(tvShow: TvResultList) {
        tvShow.isFavorite = if (tvShow.isFavorite == 1) 0 else 1
        tvShow.type = TYPE.TV_SHOW.name
        val db = AppDatabase.getInstance(requireContext())
        GlobalScope.launch {
            val favoriteData = FavoriteEntity(
                tvShow.id,
                tvShow.name,
                tvShow.overview,
                tvShow.poster_path,
                "",
                tvShow.vote_average,
                tvShow.isFavorite,
                tvShow.type
            )
            db.favoriteDao().insert(favoriteData)
            val data = db.favoriteDao().getAll()
            Log.i("zxc", data.joinToString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}