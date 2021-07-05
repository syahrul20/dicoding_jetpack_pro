package com.sf.jetpack.mymov.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sf.jetpack.mymov.adapter.TvShowsFavoriteAdapter
import com.sf.jetpack.mymov.databinding.FragmentTvShowsBinding
import com.sf.jetpack.mymov.db.AppDatabase
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.detail.DetailMovieActivity
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavoriteFragment : Fragment(), TvShowsFavoriteAdapter.ITvShow {

    private val viewModel: TvShowViewModel by viewModel()
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvShowFavoriteAdapter: TvShowsFavoriteAdapter

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

        setupObserver()
    }


    private fun setupObserver() {
        setLoading(true)
        viewModel.getAllFavoriteTvShow().observe(viewLifecycleOwner, {
            setLoading(false)
            val tvShowFavoriteList = it.filter { item -> item.type == TYPE.TV_SHOW.name }
            tvShowFavoriteAdapter = TvShowsFavoriteAdapter(tvShowFavoriteList, this@TvShowFavoriteFragment)
            binding.rvTvShows.adapter = tvShowFavoriteAdapter
        })
    }

    override fun onTvShowClicked(tvShow: FavoriteEntity) {
        Intent(requireActivity(), DetailMovieActivity::class.java).apply {
            putExtra(Extra.DATA, tvShow)
            startActivity(this)
        }
    }

    override fun onItemFavoriteClicked(movie: FavoriteEntity) {
        // TODO:
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

//    override fun onItemFavoriteClicked(movie: FavoriteEntity) {
//        movie.isFavorite = if (movie.isFavorite == 1) 0 else 1
////        val db = AppDatabase.getInstance(requireContext())
////        GlobalScope.launch {
////            val favoriteData = FavoriteEntity(
////                movie.id,
////                movie.title,
////                movie.overview,
////                movie.poster_path,
////                movie.release_date,
////                movie.vote_average,
////                movie.isFavorite
////            )
////            db.favoriteDao().insert(favoriteData)
////            val data = db.favoriteDao().getAll()
//        Log.i("zxc", data.joinToString())
//    }
//}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
