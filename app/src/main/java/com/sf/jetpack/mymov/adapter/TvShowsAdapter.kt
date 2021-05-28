package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding
import com.sf.jetpack.mymov.network.response.TvResultList
import com.sf.jetpack.mymov.utils.loadUrl

class TvShowsAdapter(
    private val itemList: List<TvResultList>,
    private val listener: ITvShow? = null
) :
    RecyclerView.Adapter<TvShowsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { tvShow ->
            binding.textName.text = tvShow.name
            binding.textDescription.text = tvShow.overview
            val rate = (tvShow.vote_average * 10) / 20
            binding.ratingBar.rating = rate.toFloat()
            binding.imageMovies.loadUrl(BuildConfig.API_URL_IMAGE_W500 + tvShow.poster_path)
            binding.root.setOnClickListener {
                listener?.onTvShowClickListener(tvShow)
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
        fun onTvShowClickListener(tvShow: TvResultList)
    }
}