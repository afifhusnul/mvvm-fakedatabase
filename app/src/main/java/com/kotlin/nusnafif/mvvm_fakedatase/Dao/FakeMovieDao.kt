package com.kotlin.nusnafif.mvvm_fakedatase.Dao


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeMovieDao {

    data class Movie(val movieTitle: String,val movieSynopsis: String) {

        override fun toString(): String {
            return "$movieTitle - $movieSynopsis"
        }
    }

    //Repository
    class MovieRepository private constructor(private val movieDao: FakeMovieDao) {
        // This may seem redundant.
        // Imagine a code which also updates and checks the backend.

        fun addMovie(movie: Movie) {
            movieDao.addMovie(movie)
        }

        fun getMovie() = movieDao.getMovies()

        companion object {
            // Singleton instantiation you already know and love
            @Volatile private var instance: MovieRepository? = null

            fun getInstance(movieDao: FakeMovieDao) =
                instance ?: synchronized(this) {
                    instance ?: MovieRepository(movieDao).also { instance = it }
                }
        }

    }

    // A fake database table
    private val movieList = mutableListOf<Movie>()
    // MutableLiveData is from the Architecture Components Library
    // LiveData can be observed for changes
    private val movies = MutableLiveData<List<Movie>>()

    init {
        // Immediately connect the now empty movielist
        // to the MutableLiveData which can be observed
        movies.value = movieList
    }

    fun addMovie(movie: Movie) {
        movieList.add(movie)
        // After adding a movie to the "database",
        // update the value of MutableLiveData
        // which will notify its active observers
        movies.value = movieList
    }

    // Casting MutableLiveData to LiveData because its value
    // shouldn't be changed from other classes
    fun getMovies() = movies as LiveData<List<Movie>>

}