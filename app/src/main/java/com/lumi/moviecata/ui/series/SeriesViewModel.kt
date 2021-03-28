package com.lumi.moviecata.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.vo.Resource

class SeriesViewModel(private val movieCataRepository: MovieCataRepository) : ViewModel() {

    fun getSeries() : LiveData<Resource<PagedList<SeriesEntity>>> = movieCataRepository.getSeries()
}