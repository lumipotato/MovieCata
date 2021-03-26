package com.lumi.moviecata.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.SeriesDetailEntity
import com.lumi.moviecata.vo.Resource

class DetailSeriesViewModel (private val movieCataRepository: MovieCataRepository) : ViewModel(){

    fun getSeriesDetail(seriesId: Int) : LiveData<Resource<SeriesDetailEntity>> = movieCataRepository.getSeriesDetail(seriesId)

}