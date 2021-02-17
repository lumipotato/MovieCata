package com.lumi.moviecata.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lumi.moviecata.data.source.remote.response.RemoteDataSource
import com.lumi.moviecata.utils.LiveDataTestUtil
import com.lumi.moviecata.viewmodel.RemoteDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock


class MovieCataRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val repository = FakeMovieCataRepository(remote)

    private val dummyMovie = RemoteDummy.getMovie()
    private val movieId = dummyMovie[0].id
    private val dummySeries = RemoteDummy.getSeries()
    private val seriesId = dummySeries[0].id

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.GetMovieCallback)
                    .onResponse(dummyMovie)
            null
        }.`when`(remote).getMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(repository.getMovie())
        verify(remote).getMovie(any())
        assertNotNull(movieEntities)
        assertEquals(dummyMovie.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getDetailMovies() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.GetMovieDetailCallback)
                    .onResponse(dummyMovie[0])
            null
        }.`when`(remote).getMovieDetail(eq(movieId), any())
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.GetMovieDetailCallback)
                    .onResponse(dummyMovie[0])
            null
        }.`when`(remote).getMovieDetail(eq(movieId), any())

        val movieEntitiesDetail = LiveDataTestUtil.getValue(repository.getMovieDetail(movieId))

        verify(remote)
                .getMovieDetail(eq(movieId), any())

        assertNotNull(movieEntitiesDetail)
        assertNotNull(movieEntitiesDetail.title)
        assertNotNull(movieEntitiesDetail.overview)
        assertEquals(dummyMovie[0].title, movieEntitiesDetail.title)
        assertEquals(dummyMovie[0].overview, movieEntitiesDetail.overview)
    }

    @Test
    fun getSeries() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.GetSeriesCallback)
                    .onResponse(dummySeries)
            null
        }.`when`(remote).getSeries(any())
        val seriesEntities = LiveDataTestUtil.getValue(repository.getSeries())
        verify(remote).getSeries(any())
        assertNotNull(seriesEntities)
        assertEquals(dummySeries.size.toLong(), seriesEntities.size.toLong())
    }

    @Test
    fun getDetailSeries() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.GetSeriesDetailCallback)
                    .onResponse(dummySeries[0])
            null
        }.`when`(remote).getSeriesDetail(eq(seriesId), any())
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.GetSeriesDetailCallback)
                    .onResponse(dummySeries[0])
            null
        }.`when`(remote).getSeriesDetail(eq(seriesId), any())

        val seriesEntitiesDetail = LiveDataTestUtil.getValue(repository.getSeriesDetail(seriesId))

        verify(remote)
                .getSeriesDetail(eq(seriesId), any())

        assertNotNull(seriesEntitiesDetail)
        assertNotNull(seriesEntitiesDetail.name)
        assertNotNull(seriesEntitiesDetail.overview)
        assertEquals(dummySeries[0].name, seriesEntitiesDetail.name)
        assertEquals(dummySeries[0].overview, seriesEntitiesDetail.overview)
    }
}