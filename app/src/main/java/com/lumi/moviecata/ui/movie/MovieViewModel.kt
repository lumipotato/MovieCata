package com.lumi.moviecata.ui.movie

import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.MovieEntity
import com.lumi.moviecata.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()
}