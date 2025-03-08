package com.bedboy.jetmovie.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.databinding.ActivityDetailBinding
import com.bedboy.jetmovie.databinding.ContentDetailMovieBinding
import com.bedboy.jetmovie.utils.ViewModelFactory
import com.bedboy.jetmovie.vo.Status

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_RESULT: String = "data"
    }

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailMovieBinding: ContentDetailMovieBinding
    private var dataTitle: String? = ""
    private var ytURL: String? = ""
    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null
    private var mMediaType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        //setContentView
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailMovieBinding = activityDetailBinding.contentDetailMovie
        setContentView(activityDetailBinding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(activityDetailBinding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, systemBars.top, 0, systemBars.bottom)
            insets
        }

        showLoading(true)

        val bundle = intent.getParcelableExtra<DataMovieTVEntity>(DATA_RESULT)
        if (bundle != null) {
            val dataID = bundle.id
            dataTitle = bundle.title
            mMediaType = bundle.media_type.toString()
            viewModel.selectedData(dataID)
            populateDetailContent(mMediaType)
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

    private fun populateDetailContent(mediaType: String) {
        if (mediaType == "tv") {
            viewModel.getDetailTV.observe(this, { result ->
                when (result.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> if (result.data != null) {
                        showLoading(false)
                        showDetail("tv", result.data)
                    }

                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(this, "DetailTV: Failed Load Detail", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        } else {
            viewModel.getDetailMovie.observe(this, { result ->
                when (result.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> if (result.data != null) {
                        showLoading(false)
                        showDetail("movie", result.data)
                    }

                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(this, "DetailMovie: Failed Load Detail", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showDetail(
        mediaType: String,
        data: DataMovieTVEntity
    ) {
        detailMovieBinding.apply {
            tvCategoryFilmDetail.text = data.genre?.replace(",", " | ")
            tvTitleFilmDetail.text = data.title
            tvRatingFilmDetail.text = data.vote.toString()
            tvDescriptionFilmDetail.text = data.overview

            activityDetailBinding.btnRemind.setOnClickListener {
                viewModel.addToRemind(
                    tvTitleFilmDetail.text.toString(),
                    tvDescriptionFilmDetail.text.toString(),
                    data.releaseData ?: 0
                )
            }

            viewModel.getVideos(mediaType, data.id)
                .observe(this@DetailActivity, { result ->
                    wvYoutube.apply {
                        settings.javaScriptEnabled = true
                        webChromeClient = object : WebChromeClient() {}
                        if (result.data?.size != 0) {
                            result.data?.get(0)?.key.let {
                                ytURL = it
                                loadUrl("https://www.youtube.com/embed/$it")
                            }
                        } else {
                            loadUrl("https://www.youtube.com/embed/6vdMiG_syOE")
                        }
                    }


                })

        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            activityDetailBinding.shimmerDetail.startShimmer()
        } else {
            activityDetailBinding.apply {
                shimmerDetail.stopShimmer()
                shimmerDetail.hideShimmer()
                shimmerDetail.isGone = true

                //INIT PROPERTIES
                ablDetail.isVisible = true
                contentDetailMovie.root.isVisible = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_detail_movie, menu)
        this.menu = menu
        if (mMediaType == "tv") {
            viewModel.getDetailTV.observe(this, { result ->
                if (result != null) {
                    when (result.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> if (result.data != null) {
                            showLoading(false)
                            setFavoriteState(result.data.isFavorite)
                        }

                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(this, "DetailTV: Failed Load Detail", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })
        } else {
            viewModel.getDetailMovie.observe(this, { result ->
                if (result != null) {
                    when (result.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> if (result.data != null) {
                            showLoading(false)
                            setFavoriteState(result.data.isFavorite)
                        }

                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(
                                this,
                                "DetailMovie: Failed Load Detail",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                onShareClick()
                return true
            }

            R.id.action_watchlist -> {
                viewModel.addToWatchList()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_watchlist)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_watchlist_filled)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_watchlist_border)
        }
    }

    private fun onShareClick() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Aye, check this out... i found it from JetMovie: \n $dataTitle - https://youtu.be/$ytURL"
            )
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Bagikan aplikasi ini sekarang"))
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
