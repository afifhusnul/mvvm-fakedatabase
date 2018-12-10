package com.kotlin.nusnafif.mvvm_fakedatase.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.nusnafif.mvvm_fakedatase.Dao.FakeMovieDao

class MovieViewModel (private val movieRepository: FakeMovieDao.MovieRepository) : ViewModel() {
    private lateinit var moviesResult: LiveData<List<FakeMovieDao.Movie>>

    fun getMovies() = movieRepository.getMovie()

    fun addMovie(movie: FakeMovieDao.Movie) = movieRepository.addMovie(movie)

}