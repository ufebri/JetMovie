package com.bedboy.jetmovie.ui.home

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.databinding.ActivityMainBinding
import com.bedboy.jetmovie.databinding.ContentHomePopularBinding
import com.bedboy.jetmovie.utils.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    companion object {
        var MEDIATYPE: String = ""
        var GENRES: List<ResultsGenre>? = null
    }

    private lateinit var detailContentHomePopularBinding: ContentHomePopularBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        val homeBinding = ActivityMainBinding.inflate(layoutInflater)
        detailContentHomePopularBinding = homeBinding.detailContentHomePopular
        setContentView(homeBinding.root)

        initToolbar(homeBinding) // Setup Toolbar

        initPropertyMovies(homeBinding, viewModel)
        initPropertyTVShow(homeBinding, viewModel)

    }

    private fun initToolbar(homeBinding: ActivityMainBinding) {
        setSupportActionBar(homeBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = ""
    }

    private fun initPropertyMovies(homeBinding: ActivityMainBinding, viewModel: HomeViewModel) {
        viewModel.popular.observe(this, { result ->
            val adapter = MoviesAdapter()
            adapter.setMovies(result)

            with(homeBinding.detailContentHomePopular.rvResultsMovie) {
                layoutManager =
                    GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        })

    }

    private fun initPropertyTVShow(homeBinding: ActivityMainBinding, viewModel: HomeViewModel) {
        viewModel.trending.observe(this, { result ->
            val adapters = ImageSliderAdapter(result, this)
            homeBinding.vpHome.adapter = adapters
            homeBinding.vpHome.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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

        })

        viewModel.genre.observe(this, { result ->
            GENRES = result
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
}
