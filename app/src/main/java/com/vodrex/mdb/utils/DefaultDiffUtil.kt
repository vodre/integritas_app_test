package com.ensar.tmdbkotlin.utils

import androidx.recyclerview.widget.DiffUtil

class DefaultDiffUtil<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = false

    override fun areContentsTheSame(oldItem: T, newItem: T) = false
}
