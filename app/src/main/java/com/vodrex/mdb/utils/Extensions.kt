package com.ensar.tmdbkotlin.utils


import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import android.net.ConnectivityManager



@BindingAdapter("android:imageUrl")
fun ImageView.setImageUrl(url: String) {
    Picasso.get()
            .load("https://image.tmdb.org/t/p/w500" + url)
            .into(this)
}

fun Activity.isNetworkAvailable() : Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}