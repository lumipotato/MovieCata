package com.lumi.moviecata.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.data.source.local.entity.SeriesEntity
import com.lumi.moviecata.vo.Resource

class DetailSeriesViewModel (private val movieCataRepository: MovieCataRepository) : ViewModel(){

    private val seriesId = MutableLiveData<Int>()

    fun setSelectedSeries(seriesId: Int) {
        this.seriesId.value = seriesId
    }

    var mSeries: LiveData<Resource<SeriesEntity>> =
            Transformations.switchMap(seriesId) { mSeriesId ->
                movieCataRepository.getSeriesDetail(mSeriesId)
            }

    fun setBookmark() {
        val seriesResource = mSeries.value
        if (seriesResource != null) {
            val seriesEntity = seriesResource.data

            if (seriesEntity != null) {
                val newState = !seriesEntity.bookmarked
                movieCataRepository.setSeriesBookmark(seriesEntity, newState)
            }
        }
    }

}