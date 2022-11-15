package com.example.githublist.di

import android.content.Context
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.githublist.api.GithubService
import com.example.githublist.repository.GithubRepository
import com.example.githublist.repository.GithubRepositoryImpl
import com.example.githublist.ui.main.MainViewModel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient).baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()
}

private fun createPicassoAuth(context: Context, okHttpClient: OkHttpClient): Picasso {
    return Picasso
        .Builder(context)
        .downloader(OkHttp3Downloader(okHttpClient))
        .build()
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val repositoryModule = module {
    single<GithubRepository> {
        GithubRepositoryImpl(get())
    }
}

val networkModule = module {
    single { createNetworkClient(get()).create(GithubService::class.java) }
    single { createPicassoAuth(get(), get()) }
}