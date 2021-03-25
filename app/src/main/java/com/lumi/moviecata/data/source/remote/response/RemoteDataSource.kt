package com.lumi.moviecata.data.source.remote.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lumi.moviecata.BuildConfig
import com.lumi.moviecata.data.source.remote.ApiResponse
import com.lumi.moviecata.utils.espresso.EspressoIdlingResource
import com.lumi.moviecata.data.source.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    private val request = ApiConfig.provide()

    fun getMovie() : LiveData<ApiResponse<List<MovieItem>>>{
        EspressoIdlingResource.increment()
        val itemMovie = MutableLiveData<ApiResponse<List<MovieItem>>>()

            request.getMovie(BuildConfig.API_KEY).enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("error",t.toString())
                }

                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    itemMovie.value = response.body()?.let { ApiResponse.success(it.results) }
                }

            })
            EspressoIdlingResource.decrement()

        return itemMovie
    }

    fun getSeries() : LiveData<ApiResponse<List<SeriesItem>>>{
        EspressoIdlingResource.increment()

        val itemSeries = MutableLiveData<ApiResponse<List<SeriesItem>>>()
        ApiResponse.success(
            request.getSeries(BuildConfig.API_KEY).enqueue(object : Callback<SeriesResponse> {
                override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                    Log.d("error",t.toString())
                }

                override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                    itemSeries.value = response.body()?.let { ApiResponse.success(it.results) }
                }

            }))
            EspressoIdlingResource.decrement()

        return itemSeries
    }

    fun getMovieDetail(movieId: Int) : LiveData<ApiResponse<MovieItem>>{
        EspressoIdlingResource.increment()

        val detailMovie = MutableLiveData<ApiResponse<MovieItem>>()
        ApiResponse.success(
            request.getMovieDetail(movieId,BuildConfig.API_KEY).enqueue(object : Callback<MovieItem> {
                override fun onFailure(call: Call<MovieItem>, t: Throwable) {
                    Log.d("error",t.toString())
                }

                override fun onResponse(call: Call<MovieItem>, response: Response<MovieItem>) {
                    detailMovie.value = response.body()?.let { ApiResponse.success(it) }
                }

            }))
            EspressoIdlingResource.decrement()

        return detailMovie
    }

    fun getSeriesDetail(tvId: Int) : LiveData<ApiResponse<SeriesItem>>{
        EspressoIdlingResource.increment()

        val detailSeries = MutableLiveData<ApiResponse<SeriesItem>>()
        ApiResponse.success(
            request.getSeriesDetail(tvId, BuildConfig.API_KEY).enqueue(object :
                Callback<SeriesItem> {
                override fun onFailure(call: Call<SeriesItem>, t: Throwable) {
                    Log.d("error", t.toString())
                }

                override fun onResponse(call: Call<SeriesItem>, response: Response<SeriesItem>) {
                    detailSeries.value = response.body()?.let { ApiResponse.success(it) }
                }

            })
        )
            EspressoIdlingResource.decrement()

        return detailSeries
    }

}