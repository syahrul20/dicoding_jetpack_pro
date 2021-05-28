package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_W500
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.loadUrl

class MoviesAdapter(
    private val itemList: List<ListData>,
    private val listener: IMovie? = null
) :
    RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { movie ->
            binding.textName.text = movie.title
            binding.textDescription.text = movie.overview
            val rate = (movie.vote_average * 10) / 20
            binding.ratingBar.rating = rate.toFloat()
            binding.imageMovies.loadUrl(API_URL_IMAGE_W500 + movie.poster_path)
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
        fun onMovieClicked(movie: ListData)
    }
}