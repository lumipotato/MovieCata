package com.lumi.moviecata.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.MovieDetailEntity
import com.lumi.moviecata.vo.Resource

class DetailMovieViewModel (private val movieCataRepository: MovieCataRepository) : ViewModel(){

    fun getMovieDetail(movieId: Int) : LiveData<Resource<MovieDetailEntity>> = movieCataRepository.getMovieDetail(movieId)

}