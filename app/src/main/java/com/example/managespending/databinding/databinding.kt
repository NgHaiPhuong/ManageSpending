package com.example.managespending.databinding

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("bind:imageUrl")
fun loadImage(view: CircleImageView, url: String?) {
    Glide.with(view.context)
        .load(Uri.parse("file:///android_asset/$url"))
        .error(Uri.parse("file:///android_asset/spend/car.png")).into(view)
}
