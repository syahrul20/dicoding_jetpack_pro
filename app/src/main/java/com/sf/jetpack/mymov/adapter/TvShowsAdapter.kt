package com.sf.jetpack.mymov.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.data.TvShowsData
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding

class TvShowsAdapter(
    private val itemList: List<TvShowsData>,
    private val context: Context,
    private val listener: IMovie? = null
) :
    RecyclerView.Adapter<TvShowsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { tvShow ->
            binding.textMovieName.text = tvShow.name
            binding.textYearMovie.text = tvShow.publishedYear
            binding.textMovieDescription.text = tvShow.description
            val movieImage = context.resources.getIdentifier(tvShow.image, null, context.packageName)
            binding.imageMovies.setImageDrawable(context.getDrawable(movieImage))
            binding.root.setOnClickListener {
                listener?.onMovieClicked(tvShow)
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
        fun onMovieClicked(tvShow: TvShowsData)
    }
}