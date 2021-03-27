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

    fun getMoviesById(movieId: Int): LiveData<MovieEntity> = mCataDao.getMoviesById(movieId)

    fun getSeriesById(tvId: Int): LiveData<SeriesEntity> = mCataDao.getSeriesById(tvId)

    fun insertMovies(movies: List<MovieEntity>) = mCataDao.insertMovies(movies)

    fun insertSeries(series: List<SeriesEntity>) = mCataDao.insertSeries(series)

    fun insertMoviesDetail(movies: MovieEntity) = mCataDao.insertMoviesDetail(movies)

    fun insertSeriesDetail(series: SeriesEntity) = mCataDao.insertSeriesDetail(series)

    fun getBookmarkedMovies(): LiveData<List<MovieEntity>> = mCataDao.getBookmarkedMovies()

    fun getBookmarkedSeries(): LiveData<List<SeriesEntity>> = mCataDao.getBookmarkedSeries()

    fun setMoviesBookmark(movies: MovieEntity, newState: Boolean) {
        movies.bookmarked = newState
        mCataDao.updateMovies(movies)
    }

    fun setSeriesBookmark(series: SeriesEntity, newState: Boolean) {
        series.bookmarked = newState
        mCataDao.updateSeries(series)
    }

}