package com.lumi.moviecata.ui.favorite.moviefavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val movieCataRepository: MovieCataRepository) : ViewModel() {

    fun getFavMovie() : LiveData<List<MovieEntity>> = movieCataRepository.getBookmarkedMovies()
}