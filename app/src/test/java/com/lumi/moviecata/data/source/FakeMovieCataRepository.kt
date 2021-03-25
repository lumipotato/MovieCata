package com.lumi.moviecata.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lumi.moviecata.data.source.remote.response.MovieItem
import com.lumi.moviecata.data.source.remote.response.RemoteDataSource
import com.lumi.moviecata.data.source.remote.response.SeriesItem

class FakeMovieCataRepository (private val remoteDataSource: RemoteDataSource) :
    MovieCataDataSource {

    override fun getMovie(): LiveData<List<MovieItem>> {
        val movieList = MutableLiveData<List<MovieItem>>()
        remoteDataSource.getMovie(object  : RemoteDataSource.GetMovieCallback {
            override fun onResponse(movieResponse: List<MovieItem>) {
                movieList.postValue(movieResponse)
            }

            override fun onFailure() {
                print("error to get movies")
            }
        })
        return movieList
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieItem> {
        val movieDetail = MutableLiveData<MovieItem>()
        remoteDataSource.getMovieDetail(movieId, object  : RemoteDataSource.GetMovieDetailCallback {
            override fun onResponse(movieResponse: MovieItem) {
                movieDetail.postValue(movieResponse)
            }

            override fun onFailure() {
                print("error to get movies")
            }

        })
        return movieDetail
    }


    override fun getSeries(): LiveData<List<SeriesItem>> {
        val seriesList = MutableLiveData<List<SeriesItem>>()

        remoteDataSource.getSeries(object : RemoteDataSource.GetSeriesCallback {
            override fun onResponse(seriesResponse: List<SeriesItem>) {
                seriesList.postValue(seriesResponse)
            }

            override fun onFailure() {
                print("error to get series")
            }

        })
        return  seriesList
    }

    override fun getSeriesDetail(seriesId: Int): LiveData<SeriesItem> {
        val seriesDetail = MutableLiveData<SeriesItem>()
        remoteDataSource.getSeriesDetail(seriesId, object  : RemoteDataSource.GetSeriesDetailCallback {
            override fun onResponse(seriesResponse: SeriesItem) {
                seriesDetail.postValue(seriesResponse)
            }

            override fun onFailure() {
                print("error to get series")
            }

        })
        return seriesDetail
    }

}