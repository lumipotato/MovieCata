package com.lumi.moviecata.ui.detail

import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.SeriesEntity
import com.lumi.moviecata.utils.DataDummy

class DetailSeriesViewModel : ViewModel() {

    fun getDetailSeries(): List<SeriesEntity> = DataDummy.generateDummySeries()
}