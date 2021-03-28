package com.lumi.moviecata.ui.detail.moviedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.utils.DataDummy
import com.lumi.moviecata.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
        val dummies = Resource.success(dummy)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummies

        `when`(repository.getMovieDetail(movieId)).thenReturn(movie)
        val detailEntities = viewModel.mMovie
        verify(repository).getMovieDetail(movieId)

        assertNotNull(detailEntities)
        assertEquals(dummy.title, detailEntities.value?.data?.title)
        assertEquals(dummy.description, detailEntities.value?.data?.title)

        viewModel.mMovie.observeForever(observer)

        verify(observer).onChanged(dummies)
    }
}