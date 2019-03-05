package com.vodrex.mdb.db.remote

import com.vodrex.mdb.db.entities.GenresResponse
import com.vodrex.mdb.db.entities.Movie
import com.vodrex.mdb.db.entities.MoviesResponse
import com.vodrex.mdb.db.entities.TrailersResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface AppService {

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: Long): Observable<Movie>

    @GET("movie/{type}")
    fun getMovieList(@Path( value = "type", encoded = true) type: String): Observable<MoviesResponse>

    @GET("genre/movie/list")
    fun getGenres(): Observable<GenresResponse>

    @GET("movie/{id}/videos")
    fun getTrailers(@Path( value = "id", encoded = true) id: Long): Observable<TrailersResponse>
}