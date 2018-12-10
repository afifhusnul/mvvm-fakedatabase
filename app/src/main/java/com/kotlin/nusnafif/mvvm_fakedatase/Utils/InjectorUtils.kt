package com.kotlin.nusnafif.mvvm_fakedatase.Utils

import com.kotlin.nusnafif.mvvm_fakedatase.Dao.FakeDatabase
import com.kotlin.nusnafif.mvvm_fakedatase.Dao.FakeMovieDao
import com.kotlin.nusnafif.mvvm_fakedatase.UI.MovieViewModelFactory

object InjectorUtils {
    // This will be called from MainActivity
    fun provideMovieViewModelFactory(): MovieViewModelFactory {
        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val movieRepository = FakeMovieDao.MovieRepository.getInstance(FakeDatabase.getInstance().movieDao)
        return MovieViewModelFactory(movieRepository)
    }
}