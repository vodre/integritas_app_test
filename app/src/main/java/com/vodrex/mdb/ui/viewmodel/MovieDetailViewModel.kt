package com.vodrex.mdb.ui.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.vodrex.mdb.ui.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var disposable = CompositeDisposable()
    val posterUrl = ObservableField("")
    val textOverview = ObservableField("")
    val title = ObservableField("")
    val movieId = ObservableInt(0)
    val isVisibleFAB = ObservableBoolean(false)
    val trailerId = ObservableField("")

    init {

    }

    fun getMovieDetails(id: Long) {
        disposable.clear()
        disposable.add(repository.getMovieDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                posterUrl.set(it.posterPath)
                textOverview.set(it.overview)
                title.set(it.title)
                movieId.set(it.id?: 0)
            })
    }

    fun getTrailers(id: Long) {
        disposable.add(repository.getTrailers(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    trailerId.set(it.first().key)
                    isVisibleFAB.set(true)
                })
    }

}