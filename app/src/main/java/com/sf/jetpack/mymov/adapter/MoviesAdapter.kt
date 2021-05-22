package com.sf.jetpack.mymov.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.data.Movie
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding

class MoviesAdapter(
    private val itemList: List<Movie>,
    private val context: Context,
    private val listener: IMovie? = null
) :
    RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { movie ->
            binding.textMovieName.text = movie.name
            binding.textYearMovie.text = movie.publishedYear
            binding.textMovieDescription.text = movie.description
            val movieImage = context.resources.getIdentifier(movie.image, null, context.packageName)
            binding.imageMovies.setImageDrawable(context.getDrawable(movieImage))
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
        fun onMovieClicked(movie: Movie)
    }
}