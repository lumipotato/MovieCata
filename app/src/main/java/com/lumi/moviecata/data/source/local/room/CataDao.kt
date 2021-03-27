package com.lumi.moviecata.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.data.source.local.entity.SeriesEntity


@Dao
interface CataDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM seriesentities")
    fun getSeries(): LiveData<List<SeriesEntity>>

    @Query("SELECT * FROM movieentities WHERE id = :movieId")
    fun getMoviesById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM seriesentities WHERE id = :tvId")
    fun getSeriesById(tvId: Int): LiveData<SeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<SeriesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesDetail(movies: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeriesDetail(series: SeriesEntity)

    @Query("SELECT * FROM movieentities where bookmarked = 1")
    fun getBookmarkedMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM seriesentities where bookmarked = 1")
    fun getBookmarkedSeries(): LiveData<List<SeriesEntity>>

    @Update
    fun updateMovies(movies: MovieEntity)

    @Update
    fun updateSeries(series: SeriesEntity)

}