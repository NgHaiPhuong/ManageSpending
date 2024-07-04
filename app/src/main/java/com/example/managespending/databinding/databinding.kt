package com.example.managespending.databinding

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException

@BindingAdapter("bind:imageUrl")
fun loadImage(view: CircleImageView, url: String?) {
    Glide.with(view.context)
        .load(Uri.parse("file:///android_asset/$url"))
        .error(Uri.parse("file:///android_asset/spend/car.png")).into(view)
}
@BindingAdapter("backgroundTint")
fun setBackgroundTint(view: Button, color: Int) {
    view.backgroundTintList = ContextCompat.getColorStateList(view.context, color)
}