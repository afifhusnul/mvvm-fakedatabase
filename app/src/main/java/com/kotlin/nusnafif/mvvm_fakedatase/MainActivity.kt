package com.kotlin.nusnafif.mvvm_fakedatase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kotlin.nusnafif.mvvm_fakedatase.Dao.FakeMovieDao
import com.kotlin.nusnafif.mvvm_fakedatase.UI.MovieViewModel
import com.kotlin.nusnafif.mvvm_fakedatase.Utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUi()
    }

    private fun initializeUi() {
        // Get the MovieViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideMovieViewModelFactory()
        // Use ViewModelProviders class to create / get already created MovieViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(MovieViewModel::class.java)

        // Observing LiveData from the MovieViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getMovies().observe(this, Observer { movies ->
            val stringBuilder = StringBuilder()
            movies.forEach { movie ->
                stringBuilder.append("$movie\n\n")
            }
            textList.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Movie and add it to DB through the ViewModel
        btnSubmit.setOnClickListener {
            val movie = FakeMovieDao.Movie(editMovieTitle.text.toString(), editMovieAuthor.text.toString())
            viewModel.addMovie(movie)
            editMovieTitle.setText("")
            editMovieAuthor.setText("")
            editMovieTitle.requestFocus()
        }
    }
}
