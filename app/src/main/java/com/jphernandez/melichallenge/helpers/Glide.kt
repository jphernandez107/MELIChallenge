package com.jphernandez.melichallenge.helpers

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jphernandez.melichallenge.R

fun displayThumbnail(url: String?, imageView: ImageView) {
    Glide.with(imageView.context)
        .load(url)
        .fitCenter()
        .placeholder(R.drawable.thumbnail_placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}