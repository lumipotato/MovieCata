package com.lumi.moviecata.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.vo.Resource

class MovieViewModel(private val movieCataRepository: MovieCataRepository) : ViewModel() {

    fun getMovie() : LiveData<Resource<List<MovieEntity>>> = movieCataRepository.getMovie()
}