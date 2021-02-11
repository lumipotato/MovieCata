package com.lumi.moviecata.data.source

import androidx.lifecycle.LiveData
import com.lumi.moviecata.data.source.remote.response.MovieItem
import com.lumi.moviecata.data.source.remote.response.SeriesItem

interface MovieCataDataSource {
    fun getMovie(): LiveData<List<MovieItem>>
    fun getMovieDetail(movieId : Int) : LiveData<MovieItem>
    fun getSeries(): LiveData<List<SeriesItem>>
    fun getSeriesDetail(seriesId:Int) : LiveData<SeriesItem>
}