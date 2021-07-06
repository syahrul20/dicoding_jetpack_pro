package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class MoviesFavoriteAdapter(
    private val listener: IMovie? = null
) :
    PagingDataAdapter<FavoriteEntity, MoviesFavoriteAdapter.ItemViewHolder>(DIFF_CALLBACK) {

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
            item!!.let { movie ->
                var isFavorite = movie.isFavorite == 1

                binding.textName.text = movie.title
                binding.textDescription.text = movie.overview
                val rate = (movie.vote_average * 10) / 20
                binding.ratingBar.rating = rate.toFloat()
                binding.imageMovies.loadUrl(API_URL_IMAGE_W500 + movie.poster_path)
                if (movie.isFavorite == 1) {
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
                    listener?.onItemFavoriteClicked(movie)
                }
                binding.root.setOnClickListener {
                    listener?.onMovieClicked(movie)
                }
            }
        } catch (e: NullPointerException) {
            return
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMoviesBinding.bind(itemView)
    }

    interface IMovie {
        fun onMovieClicked(movie: FavoriteEntity)
        fun onItemFavoriteClicked(movie: FavoriteEntity)
    }
}