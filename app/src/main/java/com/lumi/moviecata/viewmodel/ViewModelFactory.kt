package com.lumi.moviecata.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.di.Injection
import com.lumi.moviecata.ui.detail.moviedetail.DetailMovieViewModel
import com.lumi.moviecata.ui.detail.seriesdetail.DetailSeriesViewModel
import com.lumi.moviecata.ui.favorite.moviefavorite.FavoriteMovieViewModel
import com.lumi.moviecata.ui.favorite.seriesfavorite.FavoriteSeriesViewModel
import com.lumi.moviecata.ui.movie.MovieViewModel
import com.lumi.moviecata.ui.series.SeriesViewModel

class ViewModelFactory private constructor(private val mRepository: MovieCataRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(mRepository) as T
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> DetailMovieViewModel(mRepository) as T
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> FavoriteMovieViewModel(mRepository) as T
            modelClass.isAssignableFrom(SeriesViewModel::class.java) -> SeriesViewModel(mRepository) as T
            modelClass.isAssignableFrom(DetailSeriesViewModel::class.java) -> DetailSeriesViewModel(mRepository) as T
            modelClass.isAssignableFrom(FavoriteSeriesViewModel::class.java) -> FavoriteSeriesViewModel(mRepository) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}