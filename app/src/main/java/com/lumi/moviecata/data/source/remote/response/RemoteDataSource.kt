package com.lumi.moviecata.data.source.remote.response

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.lumi.moviecata.BuildConfig
import com.lumi.moviecata.utils.espresso.EspressoIdlingResource
import com.lumi.moviecata.utils.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        private const val DELAY: Long = 2000
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    private val request = ApiConfig.provide()

    fun getMovie(getMovieCallback: GetMovieCallback){
        EspressoIdlingResource.increment()

        Handler(Looper.getMainLooper()).postDelayed({
            request.getMovie(BuildConfig.API_KEY).enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("error",t.toString())
                }

                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    response.body()?.results?.let { getMovieCallback.onResponse(it) }
                }

            })

        }, DELAY
        )
    }

    fun getSeries(getSeriesCallback: GetSeriesCallback){
        EspressoIdlingResource.increment()

        Handler(Looper.getMainLooper()).postDelayed({
            request.getSeries(BuildConfig.API_KEY).enqueue(object : Callback<SeriesResponse> {
                override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                    Log.d("error",t.toString())
                }

                override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                    response.body()?.results?.let { getSeriesCallback.onResponse(it) }
                }

            })
            EspressoIdlingResource.decrement()
        }, DELAY
        )
    }

    fun getMovieDetail(movieId: Int,getMovieDetailCallback: GetMovieDetailCallback){
        EspressoIdlingResource.increment()

        Handler(Looper.getMainLooper()).postDelayed({
            request.getMovieDetail(movieId,BuildConfig.API_KEY).enqueue(object : Callback<MovieItem> {
                override fun onFailure(call: Call<MovieItem>, t: Throwable) {
                    Log.d("error",t.toString())
                }

                override fun onResponse(call: Call<MovieItem>, response: Response<MovieItem>) {
                    response.body()?.let { getMovieDetailCallback.onResponse(it) }
                }

            })

        }, DELAY
        )
    }

    fun getSeriesDetail(seriesId: Int, getTvShowDetailCallback: GetSeriesDetailCallback){
        EspressoIdlingResource.increment()

        Handler(Looper.getMainLooper()).postDelayed({
            request.getSeriesDetail(seriesId,BuildConfig.API_KEY).enqueue(object : Callback<SeriesItem> {
                override fun onFailure(call: Call<SeriesItem>, t: Throwable) {
                    Log.d("error",t.toString())
                }

                override fun onResponse(call: Call<SeriesItem>, response: Response<SeriesItem>) {
                    response.body()?.let { getTvShowDetailCallback.onResponse(it) }
                }

            })

        }, DELAY
        )
    }

    interface GetMovieCallback{
        fun onResponse(movieResponse : List<MovieItem>)
        fun onFailure()
    }

    interface GetMovieDetailCallback{
        fun onResponse(movieResponse : MovieItem)
        fun onFailure()
    }

    interface GetSeriesCallback{
        fun onResponse(seriesResponse : List<SeriesItem>)
        fun onFailure()
    }

    interface GetSeriesDetailCallback{
        fun onResponse(seriesResponse : SeriesItem)
        fun onFailure()
    }

}