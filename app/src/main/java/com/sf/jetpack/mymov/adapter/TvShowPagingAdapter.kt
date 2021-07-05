package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.network.response.TvResultList
import com.sf.jetpack.mymov.utils.loadUrl
import java.lang.NullPointerException

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

class TvShowPagingAdapter(
    private val listener: ITvShow? = null
) : PagingDataAdapter<TvResultList, TvShowPagingAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TvResultList> =
            object : DiffUtil.ItemCallback<TvResultList>() {
                override fun areItemsTheSame(
                    oldItem: TvResultList,
                    newItem: TvResultList
                ): Boolean {
                    return oldItem.name == newItem.name && oldItem.overview == newItem.overview
                }

                override fun areContentsTheSame(
                    oldItem: TvResultList,
                    newItem: TvResultList
                ): Boolean {
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
            holder.bind(item!!)
        } catch (e: NullPointerException) {
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMoviesBinding.bind(itemView)
        fun bind(item: TvResultList) {
            item.let { tvShow ->
                binding.textName.text = tvShow.name
                binding.textDescription.text = tvShow.overview
                val rate = (tvShow.vote_average * 10) / 20
                binding.ratingBar.rating = rate.toFloat()
                binding.imageMovies.loadUrl(BuildConfig.API_URL_IMAGE_W500 + tvShow.poster_path)
                var isClicked = false
                if (tvShow.isFavorite == 1) {
                    binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_active)
                } else {
                    binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_inactive)
                }
                binding.imageBookmark.setOnClickListener {
                    isClicked = !isClicked
                    if (isClicked) {
                        binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_active)
                    } else {
                        binding.imageBookmark.setImageResource(R.drawable.ic_bookmark_inactive)
                    }
                    listener?.onItemFavoriteClicked(tvShow)
                }
                binding.root.setOnClickListener {
                    listener?.onTvShowClickListener(item)
                }
            }
        }
    }

    interface ITvShow {
        fun onTvShowClickListener(tvShow: TvResultList)
        fun onItemFavoriteClicked(tvShow: TvResultList)
    }
}