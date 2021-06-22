package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.loadUrl
import java.lang.NullPointerException

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

class MoviesPagingAdapter(
    private val listener: IMovie? = null
) : PagingDataAdapter<ListData, MoviesPagingAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ListData> =
            object : DiffUtil.ItemCallback<ListData>() {
                override fun areItemsTheSame(oldItem: ListData, newItem: ListData): Boolean {
                    return oldItem.title == newItem.title && oldItem.overview == newItem.overview
                }

                override fun areContentsTheSame(oldItem: ListData, newItem: ListData): Boolean {
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
        fun bind(item: ListData) {
            item.let { movie ->
                binding.textName.text = movie.title
                binding.textDescription.text = movie.overview
                val rate = (movie.vote_average * 10) / 20
                binding.ratingBar.rating = rate.toFloat()
                binding.imageMovies.loadUrl(BuildConfig.API_URL_IMAGE_W500 + movie.poster_path)
                binding.root.setOnClickListener {
                    listener?.onMovieClicked(item)
                }
            }
        }
    }

    interface IMovie {
        fun onMovieClicked(movie: ListData)
    }
}