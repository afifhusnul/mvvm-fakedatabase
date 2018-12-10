package com.kotlin.nusnafif.mvvm_fakedatase.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.nusnafif.mvvm_fakedatase.Dao.FakeMovieDao

class MovieViewModelFactory (private val movieRepository: FakeMovieDao.MovieRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository) as T
    }
}