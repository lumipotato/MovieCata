package com.lumi.moviecata.ui.favorite.moviefavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val movieCataRepository: MovieCataRepository) : ViewModel() {

    fun getFavMovie() : LiveData<PagedList<MovieEntity>> = movieCataRepository.getBookmarkedMovies()

    fun setBookmark(movieEntity: MovieEntity) {
        val newState = !movieEntity.bookmarked
        movieCataRepository.setMoviesBookmark(movieEntity, newState)
    }
}