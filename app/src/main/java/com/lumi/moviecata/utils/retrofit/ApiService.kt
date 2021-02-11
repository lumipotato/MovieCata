package com.lumi.moviecata.utils.retrofit

import com.lumi.moviecata.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getMovie(@Query("api_key") apiKey: String?) : Call<MovieResponse>

    @GET("tv/top_rated")
    fun getSeries(@Query("api_key") apiKey: String?) : Call<SeriesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int?,
                        @Query("api_key") apiKey: String?) : Call<MovieItem>

    @GET("tv/{tv_id}")
    fun getSeriesDetail(@Path("tv_id") tvId: Int?,
                         @Query("api_key") apiKey: String?) : Call<SeriesItem>
}