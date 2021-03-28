package com.lumi.moviecata.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.lumi.moviecata.data.NetworkBoundResource
import com.lumi.moviecata.data.source.local.LocalDataSource
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.data.source.remote.ApiResponse
import com.lumi.moviecata.data.source.remote.response.MovieItem
import com.lumi.moviecata.data.source.remote.response.RemoteDataSource
import com.lumi.moviecata.data.source.remote.response.SeriesItem
import com.lumi.moviecata.utils.AppExecutors
import com.lumi.moviecata.vo.Resource

class MovieCataRepository private constructor(private val remoteDataSource: RemoteDataSource,
                                              private val localDataSource: LocalDataSource,
                                              private val appExecutors: AppExecutors) : MovieCataDataSource {

    companion object {
        @Volatile
        private var instance: MovieCataRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MovieCataRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCataRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> =
                    remoteDataSource.getMovie()
            public override fun saveCallResult(data: List<MovieItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movies = MovieEntity(response.id,
                            response.title,
                            response.overview,
                            response.posterPath,
                            false)
                    movieList.add(movies)
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieItem>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieEntity> =
                    localDataSource.getMoviesById(movieId)
            override fun shouldFetch(data: MovieEntity?): Boolean =
                    data == null
            public override fun createCall(): LiveData<ApiResponse<MovieItem>> =
                    remoteDataSource.getMovieDetail(movieId)
            public override fun saveCallResult(data: MovieItem) {
                val moviesDetail = MovieEntity(
                        data.id,
                        data.title,
                        data.overview,
                        data.posterPath,
                        false
                )
                localDataSource.insertMoviesDetail(moviesDetail)
            }
        }.asLiveData()
    }

    override fun getSeries(): LiveData<Resource<PagedList<SeriesEntity>>> {
        return object : NetworkBoundResource<PagedList<SeriesEntity>, List<SeriesItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<SeriesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllSeries(), config).build()
            }
            override fun shouldFetch(data: PagedList<SeriesEntity>?): Boolean =
                    data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<List<SeriesItem>>> =
                    remoteDataSource.getSeries()
            public override fun saveCallResult(data: List<SeriesItem>) {
                val seriesList = ArrayList<SeriesEntity>()
                for (response in data) {
                    val series = SeriesEntity(response.id,
                            response.name,
                            response.overview,
                            response.posterPath,
                            false)
                    seriesList.add(series)
                }

                localDataSource.insertSeries(seriesList)
            }
        }.asLiveData()
    }

    override fun getSeriesDetail(seriesId: Int): LiveData<Resource<SeriesEntity>> {
        return object : NetworkBoundResource<SeriesEntity, SeriesItem>(appExecutors) {
            public override fun loadFromDB(): LiveData<SeriesEntity> =
                    localDataSource.getSeriesById(seriesId)
            override fun shouldFetch(data: SeriesEntity?): Boolean =
                    data == null
            public override fun createCall(): LiveData<ApiResponse<SeriesItem>> =
                    remoteDataSource.getSeriesDetail(seriesId)
            public override fun saveCallResult(data: SeriesItem) {
                val seriesDetail = SeriesEntity(
                        data.id,
                        data.name,
                        data.overview,
                        data.posterPath,
                        false
                )
                localDataSource.insertSeriesDetail(seriesDetail)
            }
        }.asLiveData()
    }

    override fun getBookmarkedMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedMovies(), config).build()
    }


    override fun setMoviesBookmark(movies: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMoviesBookmark(movies, state) }


    override fun getBookmarkedSeries(): LiveData<PagedList<SeriesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedSeries(), config).build()
    }


    override fun setSeriesBookmark(series: SeriesEntity, state: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setSeriesBookmark(series, state) }

}