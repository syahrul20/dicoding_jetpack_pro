package com.sf.jetpack.mymov.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sf.jetpack.mymov.adapter.TvShowsAdapter
import com.sf.jetpack.mymov.databinding.FragmentTvShowsBinding
import com.sf.jetpack.mymov.detail.DetailMovieActivity
import com.sf.jetpack.mymov.detail.DetailTvShowActivity
import com.sf.jetpack.mymov.network.response.TvResultList
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment(), TvShowsAdapter.ITvShow {

    private val viewModel: TvShowViewModel by viewModel()

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

        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.getListTvShowFromApi().observe(viewLifecycleOwner, {
            tvShowAdapter = TvShowsAdapter(it.results, this)
            binding.rvTvShows.adapter = tvShowAdapter
        })
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