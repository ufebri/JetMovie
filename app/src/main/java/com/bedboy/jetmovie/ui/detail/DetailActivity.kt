package com.bedboy.jetmovie.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.MovieEntity
import com.bedboy.jetmovie.databinding.ActivityDetailBinding
import com.bedboy.jetmovie.databinding.ContentDetailMovieBinding
import com.bedboy.jetmovie.utils.DataDummy
import com.bumptech.glide.Glide

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

        initViewModel(detailMovieBinding)
    }

    private fun initViewModel(detailMovieBinding: ContentDetailMovieBinding) {
        val extras = intent.getParcelableExtra<MovieEntity>(DATA_RESULT)

        detailMovieBinding.tvTitleFilmDetail.text = extras?.title
        detailMovieBinding.tvReleaseFilmDetail.text = extras?.releaseDate
        detailMovieBinding.tvDescriptionFilmDetail.text = extras?.sinopsis

        Glide.with(this)
            .load(extras?.imagePath)
            .into(detailMovieBinding.ivPosterFilmDetail)
    }
}
