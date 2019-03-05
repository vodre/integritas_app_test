package com.vodrex.mdb.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vodrex.mdb.db.entities.Genre
import com.vodrex.mdb.db.entities.Movie
import com.vodrex.mdb.ui.MoviesRepository
import com.vodrex.mdb.utils.MovieListType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var disposable = CompositeDisposable()

    val moviesLiveData = MutableLiveData<List<Movie>>()
    val genresLiveData = MutableLiveData<List<Genre>>()

    init {
        getMovieList()
    }

    fun getMovieList(type: MovieListType = MovieListType.POPULAR) {
        disposable.clear()
        disposable.add(repository.getMovieList(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                moviesLiveData.postValue(it)
            })

        getGenres()
    }

    private fun getGenres() {
        disposable.add(repository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    genresLiveData.postValue(it)
                })
    }

    override fun onCleared() {
        disposable.dispose()
    }

    fun refreshData() {
        disposable.clear()
        getGenres()
    }
}