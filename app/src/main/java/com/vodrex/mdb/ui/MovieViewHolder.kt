package com.vodrex.mdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vodrex.mdb.R
import com.vodrex.mdb.databinding.ItemMovieBinding
import com.vodrex.mdb.db.entities.Genre
import com.vodrex.mdb.db.entities.Movie

class MovieViewHolder(private val itemBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder {

            val itemBiding = DataBindingUtil.inflate<ItemMovieBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )

            return MovieViewHolder(itemBiding)
        }
    }

    fun bind(item: Movie, genres: List<Genre>?, listener: MoviesAdapter.OnClickListener) {
        itemBinding.item = item
        itemBinding.listener = listener
        itemBinding.executePendingBindings()
        var genreText = ""
        if (genres != null) {
            item.genreIds?.forEachIndexed { index, genreId ->
                genreText += genres.find {
                    it.id == genreId
                }?.name
                if (index != item.genreIds?.size!! - 1) {
                    genreText += ", "
                }

            }
        }
    }
}