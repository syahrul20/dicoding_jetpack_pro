package com.sf.jetpack.mymov.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */
class ItemStateLoadingAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ItemLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ItemLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ItemLoadStateViewHolder {
        return ItemLoadStateViewHolder.create(parent, retry)
    }
}