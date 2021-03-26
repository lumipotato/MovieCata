package com.lumi.moviecata.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.remote.response.SeriesItem
import com.lumi.moviecata.ui.series.SeriesViewModel
import com.lumi.moviecata.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SeriesViewModelTest {

    private lateinit var viewModel: SeriesViewModel
    private val dummy = RemoteDummy.getSeries()[0]
    private val seriesId = dummy.id

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCataRepository

    @Mock
    private lateinit var listObserver: Observer<List<SeriesItem>>

    @Mock
    private lateinit var detailObserver: Observer<SeriesItem>

    @Before
    fun setUp() {
        viewModel = SeriesViewModel(repository)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getMovies() {
        val dummySeries = DataDummy.generateDummySeries()
        val series = MutableLiveData<List<SeriesItem>>()
        series.value = dummySeries as List<SeriesItem>

        `when`(repository.getSeries()).thenReturn(series)
        val seriesEntities = viewModel.getSeries().value
        verify(repository).getSeries()

        Assert.assertNotNull(seriesEntities)
        Assert.assertEquals(20, seriesEntities?.size)

        viewModel.getSeries().observeForever(listObserver)
        verify(listObserver).onChanged(dummySeries)
    }

    @Test
    fun getDetailMovies() {
        val series = MutableLiveData<SeriesItem>()
        series.value = dummy

        `when`(repository.getSeriesDetail(seriesId)).thenReturn(series)
        val detailSeriesEntities = viewModel.getSeriesDetail(seriesId)
        verify(repository).getSeriesDetail(seriesId)

        Assert.assertNotNull(detailSeriesEntities)
        Assert.assertEquals(dummy.name, detailSeriesEntities.value!!.name)
        Assert.assertEquals(dummy.overview, detailSeriesEntities.value!!.overview)

        viewModel.getSeriesDetail(seriesId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummy)
    }
}