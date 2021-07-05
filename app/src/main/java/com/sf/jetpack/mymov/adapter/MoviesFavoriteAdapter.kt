package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_W500
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.utils.loadUrl

class MoviesFavoriteAdapter(
    private val itemList: List<FavoriteEntity>,
    private val listener: IMovie? = null
) :
    RecyclerView.Adapter<MoviesFavoriteAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { movie ->
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
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMoviesBinding.bind(itemView)
    }

    interface IMovie {
        fun onMovieClicked(movie: FavoriteEntity)
        fun onItemFavoriteClicked(movie: FavoriteEntity)
    }
}