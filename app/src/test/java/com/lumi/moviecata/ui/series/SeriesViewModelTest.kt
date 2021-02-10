package com.lumi.moviecata.ui.series

import com.lumi.moviecata.viewmodel.SeriesViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SeriesViewModelTest {

    private lateinit var viewModel: SeriesViewModel
    @Before
    fun setUp() {
        viewModel = SeriesViewModel()
    }

    @Test
    fun getSeries() {
        val movieEntities = viewModel.getSeries()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(10, movieEntities.size)
    }
}