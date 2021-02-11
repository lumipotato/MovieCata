package com.lumi.moviecata.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.BuildConfig
import com.lumi.moviecata.R
import com.lumi.moviecata.data.source.remote.response.SeriesItem
import com.lumi.moviecata.databinding.ActivityDetailShowsBinding
import com.lumi.moviecata.databinding.ContentDetailShowsBinding
import com.lumi.moviecata.viewmodel.SeriesViewModel
import com.lumi.moviecata.viewmodel.ViewModelFactory

class DetailSeriesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var detailContentBinding: ContentDetailShowsBinding
    private lateinit var seriesViewModel: SeriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailShowsBinding = ActivityDetailShowsBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailShowsBinding.detailContent

        setContentView(activityDetailShowsBinding.root)

        val factory = ViewModelFactory.getInstance()
        seriesViewModel = ViewModelProvider(this, factory)[SeriesViewModel::class.java]

        val extras = intent.extras
        val seriesId = extras?.getInt(EXTRA_SERIES)
        if (seriesId != null) {
            seriesViewModel.getSeriesDetail(seriesId).observe(this) { detail ->
                if (detail != null) {
                    populateSeries(detail)
                }
            }
        }

    }
    private fun populateSeries(seriesItem: SeriesItem) {
        detailContentBinding.textTitle.text = seriesItem.name
        detailContentBinding.textDescription.text = seriesItem.overview

        Glide.with(this)
                .load("${BuildConfig.IMG_URL}${seriesItem.posterPath}")
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)
    }
}