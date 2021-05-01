package com.bedboy.jetmovie.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bedboy.jetmovie.databinding.ActivityMainBinding
import com.bedboy.jetmovie.databinding.ContentHomePopularBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var detailContentHomePopularBinding: ContentHomePopularBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeBinding = ActivityMainBinding.inflate(layoutInflater)
        detailContentHomePopularBinding = homeBinding.detailContentHomePopular
        setContentView(homeBinding.root)

        initToolbar(homeBinding) // Setup Toolbar
        initViewModel(detailContentHomePopularBinding) // Setup ViewModel
    }

    private fun initToolbar(homeBinding: ActivityMainBinding) {
        setSupportActionBar(homeBinding.toolbar)
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun initViewModel(detailContentHomePopularBinding: ContentHomePopularBinding) {
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]
        val movies = viewModel.getMovies()

        val adapter = MoviesAdapter()
        adapter.setMovies(movies)

        with(detailContentHomePopularBinding.rvResultsMovie) {
            layoutManager =
                GridLayoutManager(context, 2)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }
}
