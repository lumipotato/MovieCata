package com.lumi.moviecata.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.R
import com.lumi.moviecata.data.SeriesEntity
import com.lumi.moviecata.databinding.ActivityDetailShowsBinding
import com.lumi.moviecata.databinding.ContentDetailShowsBinding
import com.lumi.moviecata.ui.series.SeriesViewModel

class DetailSeriesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var detailContentBinding: ContentDetailShowsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailShowsBinding = ActivityDetailShowsBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailShowsBinding.detailContent

        setContentView(activityDetailShowsBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val seriesId = extras.getString(EXTRA_SERIES)
            if (seriesId != null) {
                val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[SeriesViewModel::class.java]
                val series = viewModel.getSeries()
                for (serie in series) {
                    if (serie.seriesId == seriesId) {
                        populateSeries(serie)
                    }
                }
            }
        }

    }
    private fun populateSeries(seriesEntity: SeriesEntity) {
        detailContentBinding.textTitle.text = seriesEntity.title
        detailContentBinding.textDescription.text = seriesEntity.description

        Glide.with(this)
                .load(seriesEntity.imagePath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding.imagePoster)
    }
}