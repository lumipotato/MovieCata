package com.lumi.moviecata.ui.detail.seriesdetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.BuildConfig
import com.lumi.moviecata.R
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.databinding.ActivityDetailShowsBinding
import com.lumi.moviecata.databinding.ContentDetailShowsBinding
import com.lumi.moviecata.viewmodel.ViewModelFactory
import com.lumi.moviecata.vo.Resource
import com.lumi.moviecata.vo.Status

class DetailSeriesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var detailContentBinding: ContentDetailShowsBinding
    private lateinit var seriesViewModel: DetailSeriesViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val showsBinding = ActivityDetailShowsBinding.inflate(layoutInflater)
        detailContentBinding = showsBinding.detailContent

        setContentView(showsBinding.root)

        val factory = ViewModelFactory.getInstance(applicationContext)
        seriesViewModel = ViewModelProvider(this, factory)[DetailSeriesViewModel::class.java]

        showLoading(true)

        val extras = intent.extras
        val seriesId = extras?.getInt(EXTRA_SERIES)
        if (seriesId != null) {
            seriesViewModel.setSelectedSeries(seriesId)
            seriesViewModel.mSeries.observe(this) { detail ->
                if (detail != null) {
                    populateSeries(detail)
                    showLoading(false)
                    detailContentBinding.imagePoster.visibility = View.VISIBLE
                    detailContentBinding.textDesc.visibility = View.VISIBLE
                }
            }
        }

        supportActionBar?.title = getString(R.string.detail2)

    }
    private fun populateSeries(seriesItem: Resource<SeriesEntity>) {
        detailContentBinding.textTitle.text = seriesItem.data?.title
        detailContentBinding.textDescription.text = seriesItem.data?.description

        Glide.with(this)
                .load("${BuildConfig.IMG_URL}${seriesItem.data?.imagePath}")
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        seriesViewModel.mSeries.observe(this, { series ->
            if (series != null) {
                when (series.status) {
                    Status.LOADING -> detailContentBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> if (series.data != null) {
                        detailContentBinding.progressBar.visibility = View.GONE
                        val state = series.data.bookmarked
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
        return when (item.itemId) {
            R.id.action_fav -> {
                seriesViewModel.setBookmark()
                true
            }

            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> true
        }
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