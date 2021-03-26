package com.lumi.moviecata.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lumi.moviecata.data.source.local.entity.MovieDetailEntity
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.data.source.local.entity.SeriesDetailEntity
import com.lumi.moviecata.data.source.local.entity.SeriesEntity


@Dao
interface CataDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM seriesentities")
    fun getSeries(): LiveData<List<SeriesEntity>>

    @Query("SELECT * FROM moviedetails WHERE id = :movieId")
    fun getMoviesById(movieId: Int): LiveData<MovieDetailEntity>

    @Query("SELECT * FROM seriesdetails WHERE id = :tvId")
    fun getSeriesById(tvId: Int): LiveData<SeriesDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<SeriesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesDetail(movies: MovieDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeriesDetail(series: SeriesDetailEntity)

    @Query("SELECT * FROM movieentities where bookmarked = 1")
    fun getBookmarkedMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM seriesentities where bookmarked = 1")
    fun getBookmarkedSeries(): LiveData<List<SeriesEntity>>

    @Update
    fun updateMovies(movies: MovieEntity)

    @Update
    fun updateSeries(series: SeriesEntity)

}