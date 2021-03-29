package com.lumi.moviecata.ui.detail.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.utils.DataDummy
import com.lumi.moviecata.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummy = DataDummy.generateDummyMovies()[0]
    private val movieId = dummy.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCataRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(repository)
        viewModel.setSelectedMovie(movieId)

    }

    @Test
    fun getMovieDetail() {
        val resource: Resource<MovieEntity> = Resource.success(dummy)
        val movieEntities = MutableLiveData<Resource<MovieEntity>>()
        movieEntities.value = resource

        `when`(repository.getMovieDetail(movieId)).thenReturn(movieEntities)

        viewModel.setSelectedMovie(movieId)
        viewModel.mMovie.observeForever(observer)

        verify(observer).onChanged(resource)
    }

    @Test
    fun favoriteMovie() {
        val dummyMovieFavorite = Resource.success(dummy)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        val newState = !dummy.bookmarked
        movie.value = dummyMovieFavorite
        `when`(repository.getMovieDetail(movieId)).thenReturn(movie)

        doNothing().`when`(repository).setMoviesBookmark(dummy, newState)
        viewModel.mMovie.observeForever(observer)
        viewModel.setBookmark()
        verify(repository, times(1)).setMoviesBookmark(dummy, newState)
    }
}