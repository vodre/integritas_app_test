package com.vodrex.mdb.db

import android.util.Log
import com.vodrex.mdb.db.entities.Movie
import com.vodrex.mdb.db.entities.Trailer
import com.vodrex.mdb.db.local.AppDatabase
import com.vodrex.mdb.db.remote.AppService
import com.vodrex.mdb.utils.MovieListType
import com.vodrex.mdb.utils.getValue
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MovieRepository(private val remote: AppService, private val local: AppDatabase) {


    fun getMovie(id: Long): Observable<Movie> {
        return local.movieDao().getMovie(id)
            .toObservable()
            .doOnNext {
            }.doOnError {
                Log.e("Error!!!!" , "${it.message}")
            }
    }

    fun getMovieList(listType: MovieListType): Observable<List<Movie>> {
        return Observable.concatArray(
            getMoviesFromDb(listType),
            getMoviesFromApi(listType).materialize()
                .filter { !it.isOnError }
                .dematerialize<List<Movie>>()
        )
    }

    fun getTrailers(id: Long): Observable<List<Trailer>> {
        return remote.getTrailers(id)
            .map { it.trailers }
            .doOnNext { trailers ->
                Log.d("Dispatching"," ${trailers.size} from API...")
            }.doOnError {
                Log.d("Error!!!!"," ${it.message}")
            }
    }

    private fun getMoviesFromApi(listType: MovieListType): Observable<List<Movie>> {
        return remote.getMovieList(listType.getValue())
            .map { it.movies }
            .doOnNext { movies ->
                Log.d("Dispatching"," ${movies.size} from API...")
                movies.forEach { movie -> movie.listType = listType.getValue() }
                storeMoviesInDb(movies)
            }.doOnError {
                Log.d("Error!!!!"," ${it.message}")
            }
    }

    private fun getMoviesFromDb(listType: MovieListType): Observable<List<Movie>> {
        return local.movieDao().getMovies(listType.getValue())
            .toObservable()
            .doOnNext {
                Log.d("Dispatching"," ${it.size} from DB...")
            }.doOnError {
                Log.e("Error!!!!" , "${it.message}")
            }
    }

    private fun storeMoviesInDb(movies: List<Movie>) {
        movies.forEachIndexed { index, movie -> movie.orderId = index }
        Observable.fromCallable { local.movieDao().insertMovies(movies) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Log.d("Inserted"," ${movies.size} users from API in DB...")
                }
    }
}