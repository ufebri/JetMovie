package com.bedboy.jetmovie.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.databinding.ActivityDetailBinding
import com.bedboy.jetmovie.databinding.ContentDetailMovieBinding
import com.bedboy.jetmovie.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_RESULT: String = "data"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailMovieBinding: ContentDetailMovieBinding
    private lateinit var genres: List<ResultsGenre>
    private var dataTitle: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        //setContentView
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailMovieBinding = activityDetailBinding.contentDetailMovie
        setContentView(activityDetailBinding.root)

        val bundle = intent.getParcelableExtra<ResultsItem>(DATA_RESULT)
        if (bundle != null) {
            dataTitle = bundle.name ?: bundle.title
            populateDetailContent(bundle, viewModel)
        }

        initToolbar()
    }

    private fun initToolbar() {
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

    @SuppressLint("SetJavaScriptEnabled")
    private fun populateDetailContent(bundle: ResultsItem, data: DetailViewModel) {


        detailMovieBinding.apply {

            data.getGenre(bundle.mediaType).observe(this@DetailActivity, { result ->
                genres = result
                tvCategoryFilmDetail.text = convertGenre(bundle.genreIds).replace(",", " | ")
            })

            tvTitleFilmDetail.text = bundle.name ?: bundle.title
            tvRatingFilmDetail.text = bundle.voteAverage.toString()
            tvDescriptionFilmDetail.text = bundle.overview

            data.getvideos(bundle.mediaType, bundle.id.toString())
                .observe(this@DetailActivity, { result ->
                    wvYoutube.apply {
                        settings.javaScriptEnabled = true
                        webChromeClient = object : WebChromeClient() {}
                        loadUrl("https://www.youtube.com/embed/${result[0].key}")
                    }

                    //STOP SHIMMER
                    activityDetailBinding.shimmerDetail.stopShimmer()
                    activityDetailBinding.shimmerDetail.hideShimmer()
                    activityDetailBinding.shimmerDetail.isGone = true

                    //INIT PROPERTIES
                    activityDetailBinding.ablDetail.isVisible = true
                    activityDetailBinding.contentDetailMovie.root.isVisible = true
                })

        }
    }

    private fun convertGenre(genreID: List<Int>): String {
        val filteredGenre = ArrayList<ResultsGenre>()
        for (id in genreID) {
            val genre = genres.find { it.id == id }
            if (genre != null)
                filteredGenre.add(genre)
        }
        return filteredGenre.joinToString { it.name }
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


    override fun onResume() {
        super.onResume()
        activityDetailBinding.shimmerDetail.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        activityDetailBinding.shimmerDetail.stopShimmer()
    }
}
