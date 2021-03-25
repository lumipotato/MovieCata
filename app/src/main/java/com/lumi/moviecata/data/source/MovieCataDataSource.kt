package com.lumi.moviecata.data.source

import androidx.lifecycle.LiveData
import com.lumi.moviecata.data.source.remote.response.MovieItem
import com.lumi.moviecata.data.source.remote.response.SeriesItem
import com.lumi.moviecata.vo.Resource

interface MovieCataDataSource {
    fun getMovie(): LiveData<Resource<List<MovieItem>>>
    fun getMovieDetail(movieId : Int) : LiveData<Resource<MovieItem>>
    fun getSeries(): LiveData<Resource<List<SeriesItem>>>
    fun getSeriesDetail(seriesId:Int) : LiveData<Resource<SeriesItem>>
    fun insertMovies(movieId : Int) : LiveData<Resource<MovieItem>>
    fun insertSeries(seriesId:Int) : LiveData<Resource<SeriesItem>>
    fun deleteMovies(movieId : Int) : LiveData<Resource<MovieItem>>
    fun deleteSeries(seriesId:Int) : LiveData<Resource<SeriesItem>>
}