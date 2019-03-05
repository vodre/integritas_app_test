package com.vodrex.mdb.db

import com.vodrex.mdb.db.entities.Genre
import com.vodrex.mdb.db.local.AppDatabase
import com.vodrex.mdb.db.remote.AppService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class GenreRepository(private val remote: AppService, private val local: AppDatabase) {

    fun getGenres(): Observable<List<Genre>> {
        return Observable.concatArray(
                getGenresFromDb(),
                getGenresFromApi().materialize()
                        .filter { !it.isOnError }
                        .dematerialize<List<Genre>>()
        )
    }

    private fun getGenresFromDb(): Observable<List<Genre>> {
        return local.genreDao().getGenres()
                .toObservable()
                .doOnNext {
                }.doOnError {
                }
    }

    private fun getGenresFromApi(): Observable<List<Genre>> {
        return remote.getGenres()
                .map { it.genres }
                .doOnNext {
                    storeGenresInDb(it)
                }.doOnError {
                }
    }

    private fun storeGenresInDb(genres: List<Genre>) {
        Observable.fromCallable { local.genreDao().insertGenres(genres) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                }
    }
}