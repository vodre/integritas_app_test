package com.vodrex.mdb.utils

enum class MovieListType () {
    POPULAR, TOP_RATED, UPCOMING
}

fun MovieListType.getValue() : String{
    return when(this){
        MovieListType.POPULAR -> "popular"
        MovieListType.TOP_RATED -> "top_rated"
        MovieListType.UPCOMING -> "upcoming"
    }
}