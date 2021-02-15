package com.lumi.moviecata.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.BuildConfig
import com.lumi.moviecata.R
import com.lumi.moviecata.data.source.remote.response.MovieItem
import com.lumi.moviecata.databinding.ActivityDetailShowsBinding
import com.lumi.moviecata.databinding.ContentDetailShowsBinding
import com.lumi.moviecata.viewmodel.MovieViewModel
import com.lumi.moviecata.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var detailContentBinding: ContentDetailShowsBinding
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailShowsBinding = ActivityDetailShowsBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailShowsBinding.detailContent

        setContentView(activityDetailShowsBinding.root)

        val factory = ViewModelFactory.getInstance()
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        showLoading(true)

        val extras = intent.extras
        val movieId = extras?.getInt(EXTRA_MOVIE)
        if (movieId != null) {
            movieViewModel.getMovieDetail(movieId).observe(this) { detail ->
                if (detail != null) {
                    populateMovie(detail)
                    showLoading(false)
                    detailContentBinding.imagePoster.visibility = View.VISIBLE
                    detailContentBinding.textDesc.visibility = View.VISIBLE
                }
            }
        }

    }
    private fun populateMovie(movieItem: MovieItem) {
        detailContentBinding.textTitle.text = movieItem.title
        detailContentBinding.textDescription.text = movieItem.overview

        Glide.with(this)
                .load("${BuildConfig.IMG_URL}${movieItem.posterPath}")
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detailContentBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailContentBinding.progressBar.visibility = View.INVISIBLE
        }
    }
}