package com.vodrex.mdb.ui

import com.vodrex.mdb.db.GenreRepository
import com.vodrex.mdb.db.MovieRepository
import com.vodrex.mdb.db.entities.Genre
import com.vodrex.mdb.db.entities.Movie
import com.vodrex.mdb.db.entities.Trailer
import com.vodrex.mdb.utils.MovieListType
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class MoviesRepository(private val movieRepository: MovieRepository,
                       private val genreRepository: GenreRepository
) {

    fun getMovieList(type: MovieListType): Observable<List<Movie>> = movieRepository
        .getMovieList(type).debounce(400, TimeUnit.MILLISECONDS)

    fun getGenres(): Observable<List<Genre>> = genreRepository.getGenres()
            .debounce(400, TimeUnit.MILLISECONDS)

    fun getMovieDetails(id: Long): Observable<Movie> = movieRepository
        .getMovie(id).debounce(400, TimeUnit.MILLISECONDS)

    fun getTrailers(id: Long): Observable<List<Trailer>> = movieRepository
        .getTrailers(id).debounce(400, TimeUnit.MILLISECONDS)
}