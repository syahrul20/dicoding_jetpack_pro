package com.sf.jetpack.mymov.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sf.jetpack.mymov.R

fun ImageView.loadUrl(
    imageUrl: String?,
    placeholder: Int = R.drawable.ic_placeholder_view_vector
) {
    if (imageUrl.isNullOrEmpty()) {
        Glide.with(this)
            .load(placeholder)
            .into(this)
        return
    }
    Glide.with(this)
        .load(imageUrl)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}