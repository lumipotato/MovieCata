package com.lumi.moviecata.ui.favorite.seriesfavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.SeriesEntity

class FavoriteSeriesViewModel(private val movieCataRepository: MovieCataRepository) : ViewModel() {

    fun getFavSeries() : LiveData<List<SeriesEntity>> = movieCataRepository.getBookmarkedSeries()
}