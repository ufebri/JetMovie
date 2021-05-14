package com.bedboy.jetmovie.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.data.source.remote.response.ResultsVideos
import com.bedboy.jetmovie.databinding.ActivityDetailBinding
import com.bedboy.jetmovie.databinding.ContentDetailMovieBinding
import com.bedboy.jetmovie.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_RESULT: String = "data"
        var dataTitle: String = "title"
        var dataID: String = "1"
        var mediaType: String = "movie"
    }

    private lateinit var detailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        //setContentView
        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailMovieBinding = activityDetailBinding.contentDetailMovie
        setContentView(activityDetailBinding.root)

        initViewModel(viewModel)
        initToolbar(activityDetailBinding)
    }

    private fun initToolbar(activityDetailBinding: ActivityDetailBinding) {
        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            elevation = 0f
            title = dataTitle
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initViewModel(viewModel: DetailViewModel) {
        val bundle = intent.getParcelableExtra<ResultsItem>(DATA_RESULT)
        if (bundle != null) {
            dataID = bundle.id.toString()
            mediaType = bundle.mediaType
            populateDetailContent(bundle, viewModel.videos)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun populateDetailContent(bundle: ResultsItem, data: LiveData<List<ResultsVideos>>) {
        dataTitle = bundle.name ?: bundle.title
        detailMovieBinding.apply {
            tvTitleFilmDetail.text = bundle.name ?: bundle.title
            tvCategoryFilmDetail.text = bundle.genreIds.toString()
            tvRatingFilmDetail.text = bundle.voteAverage.toString()
            tvDescriptionFilmDetail.text = bundle.overview

            data.observe(this@DetailActivity, { result ->
                wvYoutube.apply {
                    settings.javaScriptEnabled = true
                    webChromeClient = object : WebChromeClient() {}
                    loadUrl("https://www.youtube.com/embed/${result[0].key}")
                }
            })


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
            .setText(dataTitle)
            .startChooser()
    }
}
