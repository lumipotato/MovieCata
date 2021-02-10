package com.lumi.moviecata.di

import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.remote.response.RemoteDataSource

object Injection {
    fun provideRepository(): MovieCataRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return MovieCataRepository.getInstance(remoteDataSource)
    }
}