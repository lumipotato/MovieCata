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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie_entity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series_entity: List<SeriesEntity>)

    @Query("DELETE FROM movieentities WHERE id = :id")
    fun deleteMovie(id: Int): Int

    @Query("DELETE FROM seriesentities WHERE id = :id")
    fun deleteSeries(id: Int): Int

}