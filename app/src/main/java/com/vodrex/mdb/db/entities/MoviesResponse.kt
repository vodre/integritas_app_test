package com.vodrex.mdb.db.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vodrex.mdb.db.entities.Movie

data class MoviesResponse(
        @SerializedName("results")
        @Expose
        var movies: List<Movie>)