package com.lumi.moviecata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.remote.response.SeriesItem

class SeriesViewModel(private val movieCataRepository: MovieCataRepository) : ViewModel() {

    fun getSeries() : LiveData<List<SeriesItem>> = movieCataRepository.getSeries()
    fun getSeriesDetail(seriesId: Int) : LiveData<SeriesItem> = movieCataRepository.getSeriesDetail(seriesId)
}