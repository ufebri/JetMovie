package com.bedboy.jetmovie.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.databinding.ActivityMainBinding
import com.bedboy.jetmovie.ui.watchlist.WatchListActivity
import com.bedboy.jetmovie.utils.ViewModelFactory
import com.bedboy.jetmovie.vo.Status

class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        initToolbar() // Setup Toolbar
        showLoading(true)
        initPropertyMovies(viewModel)
        initPropertyTVShow(viewModel)
        gotoWatchList()
    }

    private fun gotoWatchList() {
        homeBinding.detailContentHomePopular.tvPopularHomeMore.setOnClickListener {
            startActivity(Intent(this, WatchListActivity::class.java))
        }
    }

    private fun initToolbar() {
        setSupportActionBar(homeBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = ""
    }

    private fun initPropertyMovies(viewModel: HomeViewModel) {
        viewModel.popular().observe(this, { result ->

            if (result != null) {
                when (result.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        val adapter = MoviesAdapter()
                        adapter.setMovies(result.data)

                        with(homeBinding.detailContentHomePopular.rvResultsMovie) {
                            layoutManager =
                                GridLayoutManager(context, 2)
                            setHasFixedSize(true)
                            this.adapter = adapter
                        }

                        homeBinding.detailContentHomePopular.tvPopularHome.text =
                            getString(R.string.popular)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(this, "Trending: Failed to get Data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }


        })

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            homeBinding.shimmerHome.isVisible = true
            homeBinding.shimmerHome.startShimmer()
        } else {
            homeBinding.shimmerHome.stopShimmer()
            homeBinding.shimmerHome.hideShimmer()
            homeBinding.shimmerHome.isGone = true

            homeBinding.detailContentHomePopular.rvResultsMovie.isVisible = true
            homeBinding.detailContentHomePopular.tvPopularHome.isVisible = true
            homeBinding.ablHome.isVisible = true
            homeBinding.detailContentHomePopular.tvPopularHomeMore.isVisible = true
        }
    }

    private fun initPropertyTVShow(viewModel: HomeViewModel) {
        viewModel.trending().observe(this, { result ->

            if (result != null) {
                when (result.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        val adapters = ImageSliderAdapter(result.data!!, this)
                        homeBinding.vpHome.adapter = adapters
                        homeBinding.vpHome.addOnPageChangeListener(object :
                            ViewPager.OnPageChangeListener {
                            override fun onPageScrolled(
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int
                            ) {
                            }

                            override fun onPageSelected(position: Int) {
                                adapters.updatePageIndicator(position)
                            }

                            override fun onPageScrollStateChanged(state: Int) {

                            }

                        })
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(this, "Popular: Failed to get Data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        })

        viewModel.genre().observe(this, { result ->
            GENRES = result.data
        })
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUIAndNavigation(this)
        }
    }

    private fun hideSystemUIAndNavigation(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.setDecorFitsSystemWindows(false)
            activity.window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
    }

    override fun onResume() {
        super.onResume()
        homeBinding.shimmerHome.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        homeBinding.shimmerHome.stopShimmer()
    }

    companion object {
        var MEDIATYPE: String = ""
        var GENRES: List<GenreEntity>? = null
    }
}
