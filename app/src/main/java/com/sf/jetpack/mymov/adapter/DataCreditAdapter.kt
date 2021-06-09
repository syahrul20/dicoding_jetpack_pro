package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_ORIGINAL
import com.sf.jetpack.mymov.databinding.ItemCastBinding
import com.sf.jetpack.mymov.network.response.Cast
import com.sf.jetpack.mymov.utils.loadUrl

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi
 */

class DataCreditAdapter(
    private val itemList: List<Cast>
) :
    RecyclerView.Adapter<DataCreditAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        itemList[position].let { cast ->
            with(binding) {
                imageActor.loadUrl(API_URL_IMAGE_ORIGINAL + cast.profilePath)
                textCastName.text = cast.originalName
                textCastRole.text = cast.character
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCastBinding.bind(itemView)
    }
}