package com.example.githublist

import android.app.Application
import com.example.githublist.di.networkModule
import com.example.githublist.di.repositoryModule
import com.example.githublist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}