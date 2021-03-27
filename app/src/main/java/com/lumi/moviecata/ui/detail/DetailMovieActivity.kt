package com.lumi.moviecata.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.BuildConfig
import com.lumi.moviecata.R
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.databinding.ActivityDetailShowsBinding
import com.lumi.moviecata.databinding.ContentDetailShowsBinding
import com.lumi.moviecata.viewmodel.ViewModelFactory
import com.lumi.moviecata.vo.Resource
import com.lumi.moviecata.vo.Status

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var detailContentBinding: ContentDetailShowsBinding
    private lateinit var movieViewModel: DetailMovieViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailShowsBinding = ActivityDetailShowsBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailShowsBinding.detailContent

        setContentView(activityDetailShowsBinding.root)

        val factory = ViewModelFactory.getInstance(applicationContext)
        movieViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

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
    private fun populateMovie(movieItem: Resource<MovieEntity>) {
        detailContentBinding.textTitle.text = movieItem.data?.title
        detailContentBinding.textDescription.text = movieItem.data?.description

        Glide.with(this)
                .load("${BuildConfig.IMG_URL}${movieItem.data?.imagePath}")
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        movieViewModel.mMovie.observe(this, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> detailContentBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (movie.data != null) {
                        detailContentBinding.progressBar.visibility = View.GONE
                        val state = movie.data.bookmarked
                        setBookmarkState(state)
                    }
                    Status.ERROR -> {
                        detailContentBinding.progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_fav) {
            movieViewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_fav)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_36)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_outline_favorite_border_36)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            detailContentBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailContentBinding.progressBar.visibility = View.INVISIBLE
        }
    }
}