package com.lumi.moviecata.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.BuildConfig
import com.lumi.moviecata.R
import com.lumi.moviecata.data.source.local.entity.SeriesDetailEntity
import com.lumi.moviecata.databinding.ActivityDetailShowsBinding
import com.lumi.moviecata.databinding.ContentDetailShowsBinding
import com.lumi.moviecata.viewmodel.ViewModelFactory
import com.lumi.moviecata.vo.Resource

class DetailSeriesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var detailContentBinding: ContentDetailShowsBinding
    private lateinit var seriesViewModel: DetailSeriesViewModel

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
            seriesViewModel.getSeriesDetail(seriesId).observe(this) { detail ->
                if (detail != null) {
                    populateSeries(detail)
                    showLoading(false)
                    detailContentBinding.imagePoster.visibility = View.VISIBLE
                    detailContentBinding.textDesc.visibility = View.VISIBLE
                }
            }
        }

    }
    private fun populateSeries(seriesItem: Resource<SeriesDetailEntity>) {
        detailContentBinding.textTitle.text = seriesItem.data?.title
        detailContentBinding.textDescription.text = seriesItem.data?.description

        Glide.with(this)
                .load("${BuildConfig.IMG_URL}${seriesItem.data?.imagePath}")
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