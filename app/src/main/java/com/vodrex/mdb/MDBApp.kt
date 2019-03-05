package com.vodrex.mdb

import android.app.Application
import com.vodrex.mdb.di.KoinDSL
import org.koin.android.ext.android.startKoin

class MDBApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Koin
        startKoin(this, listOf(
            KoinDSL.networkModule,
            KoinDSL.movieRepositoryModule,
            KoinDSL.viewModelModule
        ))
    }
}
