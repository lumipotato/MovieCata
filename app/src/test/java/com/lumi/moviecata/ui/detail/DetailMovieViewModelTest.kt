package com.lumi.moviecata.ui.detail

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
    }

    @Test
    fun getDetailMovies() {
        val movieEntities = viewModel.getDetailMovies()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(10, movieEntities.size)
    }
}