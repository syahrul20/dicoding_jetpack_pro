package com.sf.jetpack.mymov.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.sf.jetpack.mymov.databinding.ItemLoadingFooterBinding

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

class ItemLoadStateViewHolder(
    private val binding: ItemLoadingFooterBinding,
    retry: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }
    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }
    companion object {
        fun create (parent: ViewGroup, retry: () -> Unit) : ItemLoadStateViewHolder {
            val view = ItemLoadingFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemLoadStateViewHolder(view, retry)
        }
    }
}