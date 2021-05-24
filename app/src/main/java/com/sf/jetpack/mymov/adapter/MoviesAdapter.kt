package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_ORIGINAL
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding
import com.sf.jetpack.mymov.network.response.MovieData
import com.sf.jetpack.mymov.utils.loadUrl

class MoviesAdapter(
    private val itemList: List<MovieData>,
    private val listener: IMovie? = null
) :
    RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { movie ->
            binding.textMovieName.text = movie.title
            binding.textYearMovie.text = movie.release_date
            binding.textMovieDescription.text = movie.overview
            binding.imageMovies.loadUrl(API_URL_IMAGE_ORIGINAL + movie.poster_path)
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
        fun onMovieClicked(movie: MovieData)
    }
}