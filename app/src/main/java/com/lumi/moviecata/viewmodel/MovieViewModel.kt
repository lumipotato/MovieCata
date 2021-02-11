package com.lumi.moviecata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.remote.response.MovieItem

class MovieViewModel(private val movieCataRepository: MovieCataRepository) : ViewModel() {

    fun getMovie() : LiveData<List<MovieItem>> = movieCataRepository.getMovie()
    fun getMovieDetail(movieId: String) : LiveData<MovieItem> = movieCataRepository.getMovieDetail(movieId)
}