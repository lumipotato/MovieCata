package com.lumi.moviecata.data.source.local

import androidx.lifecycle.LiveData
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.data.source.local.room.CataDao

class LocalDataSource private constructor(private val mCataDao: CataDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(cataDao: CataDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(cataDao)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mCataDao.getMovies()

    fun getAllSeries(): LiveData<List<SeriesEntity>> = mCataDao.getSeries()

    fun insertMovies(movies: List<MovieEntity>) = mCataDao.insertMovies(movies)

    fun insertSeries(series: List<SeriesEntity>) = mCataDao.insertSeries(series)

    fun deleteMovies(movieId: Int) = mCataDao.deleteMovie(movieId)

    fun deleteSeries(seriesId: Int) = mCataDao.deleteSeries(seriesId)
}