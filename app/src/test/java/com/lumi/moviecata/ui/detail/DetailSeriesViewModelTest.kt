package com.lumi.moviecata.ui.detail

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailSeriesViewModelTest {

    private lateinit var viewModel: DetailSeriesViewModel
    @Before
    fun setUp() {
        viewModel = DetailSeriesViewModel()
    }

    @Test
    fun getDetailSeries() {
        val movieEntities = viewModel.getDetailSeries()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(10, movieEntities.size)
    }
}