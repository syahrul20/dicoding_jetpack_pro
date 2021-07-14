package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_W500
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.loadUrl
import java.lang.NullPointerException

class TvShowsFavoriteAdapter(
    private val listener: ITvShow? = null
) :
    PagingDataAdapter<FavoriteEntity, TvShowsFavoriteAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteEntity> =
            object : DiffUtil.ItemCallback<FavoriteEntity>() {
                override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
                    return oldItem.title == newItem.title && oldItem.overview == newItem.overview
                }

                override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
                    return oldItem == newItem
                }
            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        try {
            item!!.let { tvShow ->
                var isFavorite = tvShow.isFavorite == 1
                binding.imageBookmark.isVisible = true
                binding.textName.text = tvShow.title
                binding.textDescription.text = tvShow.overview
                val rate = (tvShow.vote_average * 10) / 20
                binding.ratingBar.rating = rate.toFloat()
                binding.imageMovies.loadUrl(API_URL_IMAGE_W500 + tvShow.poster_path)
                if (tvShow.isFavorite == 1) {
                    binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_active)
                } else {
                    binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_inactive)
                }
                binding.imageBookmark.setOnClickListener {
                    isFavorite = !isFavorite
                    if (isFavorite) {
                        binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_active)
                    } else {
                        binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_inactive)
                    }
                    listener?.onItemFavoriteClicked(tvShow)
                }
                binding.root.setOnClickListener {
                    listener?.onTvShowClicked(tvShow)
                }
            }
        } catch (e: NullPointerException) {
            return
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMoviesBinding.bind(itemView)
    }

    interface ITvShow {
        fun onTvShowClicked(tvShow: FavoriteEntity)
        fun onItemFavoriteClicked(tvShow: FavoriteEntity)
    }
}