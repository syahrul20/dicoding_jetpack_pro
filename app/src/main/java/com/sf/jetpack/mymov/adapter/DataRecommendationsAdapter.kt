package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig
import com.sf.jetpack.mymov.databinding.ItemMovieRecommendationBinding
import com.sf.jetpack.mymov.network.response.Result
import com.sf.jetpack.mymov.utils.loadUrl


class DataRecommendationsAdapter(
    private val itemList: List<Result>,
    private val isMovie: Boolean
) :
    RecyclerView.Adapter<DataRecommendationsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMovieRecommendationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { movie ->
            with(binding) {
                textMovieName.text = if (isMovie) movie.originalTitle else movie.originalName
                imageMovie.loadUrl(BuildConfig.API_URL_IMAGE_W500 + movie.posterPath)
                val rate = movie.voteAverage.let { (it * 10) / 20 }
                ratingBar.rating = rate.toFloat()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMovieRecommendationBinding.bind(itemView)
    }
}