package com.lumi.moviecata.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.remote.response.MovieItem
import com.lumi.moviecata.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private val dummy = RemoteDummy.getMovie()[0]
    private val movieId = dummy.id

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCataRepository

    @Mock
    private lateinit var listObserver: Observer<List<MovieItem>>

    @Mock
    private lateinit var detailObserver: Observer<MovieItem>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getMovies() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MovieItem>>()
        movies.value = dummyMovies as List<MovieItem>

        `when`(repository.getMovie()).thenReturn(movies)
        val movieEntities = viewModel.getMovie().value
        verify(repository).getMovie()

        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getMovie().observeForever(listObserver)
        verify(listObserver).onChanged(dummyMovies)
    }

    @Test
    fun getDetailMovies() {
        val movies = MutableLiveData<MovieItem>()
        movies.value = dummy

        `when`(repository.getMovieDetail(movieId)).thenReturn(movies)
        val detailEntities = viewModel.getMovieDetail(movieId)
        verify(repository).getMovieDetail(movieId)

        assertNotNull(detailEntities)
        assertEquals(dummy.title, detailEntities.value!!.title)
        assertEquals(dummy.overview, detailEntities.value!!.overview)

        viewModel.getMovieDetail(movieId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummy)
    }
}