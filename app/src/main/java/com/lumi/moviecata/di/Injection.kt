package com.lumi.moviecata.di

import android.content.Context
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.LocalDataSource
import com.lumi.moviecata.data.source.local.room.CataDatabase
import com.lumi.moviecata.data.source.remote.response.RemoteDataSource
import com.lumi.moviecata.utils.AppExecutors

object Injection {
    fun provideRepository(context:Context): MovieCataRepository {

        val database = CataDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.cataDao())
        val appExecutors = AppExecutors()

        return MovieCataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}