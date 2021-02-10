package com.lumi.moviecata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lumi.moviecata.data.source.MovieCataRepository
import com.lumi.moviecata.di.Injection

class ViewModelFactory private constructor(private val mRepository: MovieCataRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(mRepository) as T
            modelClass.isAssignableFrom(SeriesViewModel::class.java) -> SeriesViewModel(mRepository) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}