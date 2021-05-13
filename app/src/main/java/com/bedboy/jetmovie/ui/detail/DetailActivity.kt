package com.bedboy.jetmovie.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.source.local.entity.DetailDataEntity
import com.bedboy.jetmovie.databinding.ActivityDetailBinding
import com.bedboy.jetmovie.databinding.ContentDetailMovieBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_RESULT: String = "data"
        var movieTitle: String = "title"
    }

    private lateinit var detailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView
        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailMovieBinding = activityDetailBinding.contentDetailMovie
        setContentView(activityDetailBinding.root)

        initViewModel()
        initToolbar(activityDetailBinding)
    }

    private fun initToolbar(activityDetailBinding: ActivityDetailBinding) {
        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            elevation = 0f
            title = movieTitle
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
                viewModel.setSelectedData(movieID)
                populateDetailContent(viewModel.getSelectedData())
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun populateDetailContent(movie: DetailDataEntity) {
        movieTitle = movie.title
        detailMovieBinding.apply {
            tvTitleFilmDetail.text = movie.title
            tvCategoryFilmDetail.text = movie.genre
            tvRatingFilmDetail.text = movie.vote
            tvDescriptionFilmDetail.text = movie.overview
            wvYoutube.apply {
                settings.javaScriptEnabled = true
                webChromeClient = object : WebChromeClient() {}
                loadUrl("https://www.youtube.com/embed/${movie.linkyt}")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_detail_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                onShareClick()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onShareClick() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Bagikan aplikasi ini sekarang.")
            .setText(movieTitle)
            .startChooser()
    }
}
