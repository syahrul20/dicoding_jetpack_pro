package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_ORIGINAL
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_W500
import com.sf.jetpack.mymov.databinding.ItemMovieCastBinding
import com.sf.jetpack.mymov.databinding.ItemMoviesBinding
import com.sf.jetpack.mymov.network.response.Cast
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.loadUrl

class MovieCreditAdapter(
    private val itemList: List<Cast>
) :
    RecyclerView.Adapter<MovieCreditAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemMovieCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { cast ->
            with(binding) {
                imageMovieActor.loadUrl(API_URL_IMAGE_ORIGINAL + cast.profilePath)
                textCastName.text = cast.originalName
                textCastRole.text = cast.character
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMovieCastBinding.bind(itemView)
    }
}