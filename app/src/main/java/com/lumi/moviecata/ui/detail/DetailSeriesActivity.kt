package com.lumi.moviecata.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lumi.moviecata.R
import com.lumi.moviecata.data.SeriesEntity
import com.lumi.moviecata.databinding.ActivityDetailMovieBinding
import com.lumi.moviecata.databinding.ContentDetailMovieBinding
import com.lumi.moviecata.utils.DataDummy

class DetailSeriesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    private lateinit var detailContentBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val seriesId = extras.getString(EXTRA_SERIES)
            if (seriesId != null) {
                for (series in DataDummy.generateDummySeries()) {
                    if (series.seriesId == seriesId) {
                        populateSeries(series)
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