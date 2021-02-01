package com.lumi.moviecata.ui.series

import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.SeriesEntity
import com.lumi.moviecata.utils.DataDummy

class SeriesViewModel : ViewModel() {

    fun getSeries(): List<SeriesEntity> = DataDummy.generateDummySeries()
}