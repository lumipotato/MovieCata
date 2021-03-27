package com.lumi.moviecata.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.vo.Resource

class DetailMovieViewModel (private val movieCataRepository: MovieCataRepository) : ViewModel(){

    private val courseId = MutableLiveData<Int>()

    fun getMovieDetail(movieId: Int) : LiveData<Resource<MovieEntity>> = movieCataRepository.getMovieDetail(movieId)

    private var courseModule: LiveData<Resource<MovieEntity>> =
            Transformations.switchMap(courseId) { mCourseId ->
                movieCataRepository.getMovieDetail(mCourseId)
            }

    fun setBookmark() {
        val moduleResource = courseModule.value
        if (moduleResource != null) {
            val courseWithModule = moduleResource.data

            if (courseWithModule != null) {
                val newState = !courseWithModule.bookmarked
                movieCataRepository.setMoviesBookmark(courseWithModule, newState)
            }
        }
    }

}