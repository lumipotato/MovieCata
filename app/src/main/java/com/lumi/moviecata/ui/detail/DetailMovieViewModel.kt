package com.lumi.moviecata.ui.detail

import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.MovieEntity
import com.lumi.moviecata.utils.DataDummy

class DetailMovieViewModel : ViewModel() {

    fun getDetailMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()
}