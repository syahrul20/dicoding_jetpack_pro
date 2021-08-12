package com.sf.jetpack.mymov.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sf.jetpack.mymov.adapter.TvShowsBookmarkAdapter
import com.sf.jetpack.mymov.databinding.FragmentTvShowBookmarkBinding
import com.sf.jetpack.mymov.db.TvShowEntity
import com.sf.jetpack.mymov.detail.DetailTvShowActivity
import com.sf.jetpack.mymov.utils.Extra
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowBookmarkFragment : Fragment(), TvShowsBookmarkAdapter.ITvShow {

    private val viewModel: TvShowViewModel by viewModel()
    private var _binding: FragmentTvShowBookmarkBinding? = null
    private val binding get() = _binding!!
    private val tvShowsBookmarkAdapter: TvShowsBookmarkAdapter by lazy {
        TvShowsBookmarkAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setupObserver()
    }

    private fun setUpView() {
        binding.rvTvShowsBookmark.adapter = tvShowsBookmarkAdapter
    }

    private fun setupObserver() {
        setLoading(true)
        viewModel.getListTvShowBookmarkPaging().observe(viewLifecycleOwner, { tvShowBookmarkList ->
            setLoading(false)
            binding.containerNoData.isVisible = tvShowBookmarkList.isEmpty()
            tvShowsBookmarkAdapter.submitList(tvShowBookmarkList)
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

    override fun onItemBookmarkClicked(tvShow: TvShowEntity) {
        tvShow.isBookmark = if (tvShow.isBookmark == 1) 0 else 1
        viewModel.saveBookmarkTvShow(tvShow)
        tvShowsBookmarkAdapter.notifyItemChanged(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
