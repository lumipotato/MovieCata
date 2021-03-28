package com.lumi.moviecata.ui.favorite.seriesfavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteSeriesViewModelTest {

    private lateinit var viewModel: FavoriteSeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<SeriesEntity>>

    @Mock
    private lateinit var pagedList: PagedList<SeriesEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteSeriesViewModel(repository)
    }

    @Test
    fun getFavSeries() {
        val dummySeries = pagedList
        `when`(dummySeries.size).thenReturn(20)
        val series = MutableLiveData<PagedList<SeriesEntity>>()
        series.value = dummySeries

        `when`(repository.getBookmarkedSeries()).thenReturn(series)
        val seriesEntities = viewModel.getFavSeries().value
        verify(repository).getBookmarkedSeries()
        Assert.assertNotNull(seriesEntities)
        Assert.assertEquals(20, seriesEntities?.size)

        viewModel.getFavSeries().observeForever(observer)
        verify(observer).onChanged(dummySeries)
    }
}