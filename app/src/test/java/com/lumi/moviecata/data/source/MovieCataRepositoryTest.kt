package com.lumi.moviecata.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.lumi.moviecata.data.source.local.LocalDataSource
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.data.source.remote.response.RemoteDataSource
import com.lumi.moviecata.utils.AppExecutors
import com.lumi.moviecata.utils.DataDummy
import com.lumi.moviecata.utils.LiveDataTestUtil
import com.lumi.moviecata.utils.PagedListUtil
import com.lumi.moviecata.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class MovieCataRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val repository = FakeMovieCataRepository(remote, local, appExecutors)

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val movieId = dummyMovie[0].movieId
    private val dummySeries = DataDummy.generateDummySeries()
    private val seriesId = dummySeries[0].tvId

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        repository.getMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(dummyMovie.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovies() {
        val movie = MutableLiveData<MovieEntity>()
        val dummy = dummyMovie[0]
        movie.value = dummy
        `when`(local.getMoviesById(movieId)).thenReturn(movie)

        val movieEntitiesDetail = LiveDataTestUtil.getValue(repository.getMovieDetail(movieId))
        verify(local).getMoviesById(movieId)

        assertNotNull(movieEntitiesDetail)
        assertNotNull(movieEntitiesDetail.data?.title)
        assertNotNull(movieEntitiesDetail.data?.description)
        assertEquals(dummyMovie[0].title, movieEntitiesDetail.data?.title)
        assertEquals(dummyMovie[0].description, movieEntitiesDetail.data?.description)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getSeries() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, SeriesEntity>
        `when`(local.getAllSeries()).thenReturn(dataSourceFactory)
        repository.getSeries()

        val seriesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummySeries()))
        verify(local).getAllMovies()
        assertNotNull(seriesEntities.data)
        assertEquals(dummyMovie.size.toLong(), seriesEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailSeries() {
        val series = MutableLiveData<SeriesEntity>()
        val dummy = dummySeries[0]
        series.value = dummy
        `when`(local.getSeriesById(seriesId)).thenReturn(series)

        val seriesEntitiesDetail = LiveDataTestUtil.getValue(repository.getSeriesDetail(seriesId))
        verify(local).getSeriesById(seriesId)

        assertNotNull(seriesEntitiesDetail)
        assertNotNull(seriesEntitiesDetail.data?.title)
        assertNotNull(seriesEntitiesDetail.data?.description)
        assertEquals(dummyMovie[0].title, seriesEntitiesDetail.data?.title)
        assertEquals(dummyMovie[0].description, seriesEntitiesDetail.data?.description)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getBookmarkedMovies()).thenReturn(dataSourceFactory)
        repository.getBookmarkedMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getBookmarkedMovies()
        assertNotNull(movieEntities)
        assertEquals(dummyMovie.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavSeries() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, SeriesEntity>
        `when`(local.getBookmarkedSeries()).thenReturn(dataSourceFactory)
        repository.getBookmarkedSeries()

        val seriesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummySeries()))
        verify(local).getBookmarkedSeries()
        assertNotNull(seriesEntities)
        assertEquals(dummySeries.size.toLong(), seriesEntities.data?.size?.toLong())
    }
}