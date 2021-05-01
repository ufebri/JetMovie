package com.bedboy.jetmovie.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.data.DetailMovieEntity
import com.bedboy.jetmovie.databinding.ActivityDetailBinding
import com.bedboy.jetmovie.databinding.ContentDetailMovieBinding
import com.bedboy.jetmovie.utils.DataDummy

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_RESULT: String = "data"
    }

    private lateinit var detailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView
        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailMovieBinding = activityDetailBinding.contentDetailMovie
        setContentView(activityDetailBinding.root)

        initViewModel()
    }

    private fun initViewModel() {

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val bundle = intent.extras
        if (bundle != null) {
            val movieID = bundle.getString(DATA_RESULT)
            if (movieID != null) {
                viewModel.setSelectedMovie(movieID)
                populateDetailContent(viewModel.getSelectedMovie())
            }
        }
    }

    private fun populateDetailContent(movie: DetailMovieEntity) {
        detailMovieBinding.tvTitleFilmDetail.text = movie.title
        detailMovieBinding.tvReleaseFilmDetail.text = movie.genre
        detailMovieBinding.tvDescriptionFilmDetail.text = movie.overview
    }
}
