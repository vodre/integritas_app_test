package com.vodrex.mdb.di

import com.vodrex.mdb.db.GenreRepository
import com.vodrex.mdb.db.MovieRepository
import com.ensar.tmdbkotlin.di.NetworkModule
import com.vodrex.mdb.ui.viewmodel.MovieDetailViewModel
import com.vodrex.mdb.ui.MoviesRepository
import com.vodrex.mdb.ui.viewmodel.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


object KoinDSL {
    val viewModelModule = module {
        viewModel { MoviesViewModel(get()) }
        viewModel { MovieDetailViewModel(get()) }
    }

    val movieRepositoryModule = module {
        single { MoviesRepository(movieRepository = get(), genreRepository = get()) }
        single { MovieRepository(remote = get(), local = get()) }
        single { GenreRepository(remote = get(), local = get()) }
    }

    val networkModule = module {
        single { NetworkModule.getRemote() }
        single { NetworkModule.getLocal(androidContext()) }
    }
}