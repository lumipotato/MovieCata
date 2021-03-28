package com.lumi.moviecata.ui.detail.seriesdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.utils.DataDummy
import com.lumi.moviecata.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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

    @Mock
    private lateinit var entity: SeriesEntity

    @Before
    fun setUp() {
        viewModel = DetailSeriesViewModel(repository)
        viewModel.setSelectedSeries(seriesId)
    }

    @Test
    fun getSeriesDetail() {
        val dummies = Resource.success(entity)
        val series = MutableLiveData<Resource<SeriesEntity>>()
        series.value = dummies

        `when`(repository.getSeriesDetail(seriesId)).thenReturn(series)
        val detailEntities = viewModel.mSeries
        verify(repository).getSeriesDetail(seriesId)

        Assert.assertNotNull(detailEntities)
        Assert.assertEquals(dummy.title, detailEntities.value?.data?.title)
        Assert.assertEquals(dummy.description, detailEntities.value?.data?.title)

        viewModel.mSeries.observeForever(observer)

        verify(observer).onChanged(dummies)
    }
}