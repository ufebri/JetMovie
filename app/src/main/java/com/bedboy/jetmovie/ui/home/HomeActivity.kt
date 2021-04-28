package com.bedboy.jetmovie.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bedboy.jetmovie.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]
        val movies = viewModel.getMovies()

        val adapter = MoviesAdapter()
        adapter.setMovies(movies)

        with(homeBinding.rvResultsMovie) {
            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            this.adapter = adapter
        }

    }
}
