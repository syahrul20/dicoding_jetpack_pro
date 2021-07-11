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

class TvShowsFavoriteAdapter(
    private val itemList: List<FavoriteEntity>,
    private val listener: ITvShow? = null
) :
    RecyclerView.Adapter<TvShowsFavoriteAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { tvShow ->

            binding.textName.text = tvShow.title
            binding.textDescription.text = tvShow.overview
            val rate = (tvShow.vote_average * 10) / 20
            binding.ratingBar.rating = rate.toFloat()
            binding.imageMovies.loadUrl(API_URL_IMAGE_W500 + tvShow.poster_path)
//            if (tvShow.isFavorite == 1) {
//                binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_active)
//            } else {
//                binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_inactive)
//            }
//            binding.imageBookmark.setOnClickListener {
//                isFavorite = !isFavorite
//                if (isFavorite) {
//                    binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_active)
//                } else {
//                    binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_inactive)
//                }
//                listener?.onItemFavoriteClicked(tvShow)
//            }
            binding.root.setOnClickListener {
                listener?.onTvShowClicked(tvShow)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMoviesBinding.bind(itemView)
    }

    interface ITvShow {
        fun onTvShowClicked(tvShow: FavoriteEntity)
        fun onItemFavoriteClicked(movie: FavoriteEntity)
    }
}