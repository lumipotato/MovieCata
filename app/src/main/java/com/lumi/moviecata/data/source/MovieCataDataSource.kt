package com.lumi.moviecata.data.source

import androidx.lifecycle.LiveData
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.vo.Resource

interface MovieCataDataSource {
    fun getMovie(): LiveData<Resource<List<MovieEntity>>>
    fun getMovieDetail(movieId : Int) : LiveData<Resource<MovieEntity>>
    fun getSeries(): LiveData<Resource<List<SeriesEntity>>>
    fun getSeriesDetail(seriesId:Int) : LiveData<Resource<SeriesEntity>>
    fun getBookmarkedMovies(): LiveData<List<MovieEntity>>
    fun setMoviesBookmark(movies: MovieEntity, state: Boolean)
    fun getBookmarkedSeries(): LiveData<List<SeriesEntity>>
    fun setSeriesBookmark(series: SeriesEntity, state: Boolean)
}