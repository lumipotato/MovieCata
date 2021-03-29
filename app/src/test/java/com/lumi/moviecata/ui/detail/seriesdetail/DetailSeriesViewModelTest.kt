package com.lumi.moviecata.ui.detail.seriesdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
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
class DetailSeriesViewModelTest {
    private lateinit var viewModel: DetailSeriesViewModel
    private val dummy = DataDummy.generateDummySeries()[0]
    private val seriesId = dummy.tvId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCataRepository

    @Mock
    private lateinit var observer: Observer<Resource<SeriesEntity>>

    @Before
    fun setUp() {
        viewModel = DetailSeriesViewModel(repository)
        viewModel.setSelectedSeries(seriesId)
    }

    @Test
    fun getSeriesDetail() {
        val resource: Resource<SeriesEntity> = Resource.success(dummy)
        val seriesEntities = MutableLiveData<Resource<SeriesEntity>>()
        seriesEntities.value = resource

        `when`(repository.getSeriesDetail(seriesId)).thenReturn(seriesEntities)

        viewModel.setSelectedSeries(seriesId)
        viewModel.mSeries.observeForever(observer)

        verify(observer).onChanged(resource)
    }

    @Test
    fun favoriteSeries() {
        val dummySeriesFavorite = Resource.success(dummy)
        val series = MutableLiveData<Resource<SeriesEntity>>()
        val newState = !dummy.bookmarked
        series.value = dummySeriesFavorite
        `when`(repository.getSeriesDetail(seriesId)).thenReturn(series)

        doNothing().`when`(repository).setSeriesBookmark(dummy, newState)
        viewModel.mSeries.observeForever(observer)
        viewModel.setBookmark()
        verify(repository, times(1)).setSeriesBookmark(dummy, newState)
    }
}