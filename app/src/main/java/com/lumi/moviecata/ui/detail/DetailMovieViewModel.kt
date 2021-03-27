package com.lumi.moviecata.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.vo.Resource

class DetailMovieViewModel (private val movieCataRepository: MovieCataRepository) : ViewModel(){

    fun getMovieDetail(movieId: Int) : LiveData<Resource<MovieEntity>> = movieCataRepository.getMovieDetail(movieId)

    private val movieId = MutableLiveData<Int>()
    var mMovie: LiveData<Resource<MovieEntity>> =
            Transformations.switchMap(movieId) { mMovieId ->
                movieCataRepository.getMovieDetail(mMovieId)
            }

    fun setBookmark() {
        val movieResource = mMovie.value
        if (movieResource != null) {
            val movieEntity = movieResource.data

            if (movieEntity != null) {
                val newState = !movieEntity.bookmarked
                movieCataRepository.setMoviesBookmark(movieEntity, newState)
            }
        }
    }

}