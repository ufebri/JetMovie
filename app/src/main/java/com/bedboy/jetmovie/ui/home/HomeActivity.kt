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
import com.bedboy.jetmovie.data.DataEntity
import com.bedboy.jetmovie.data.FeaturedEntity
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
        initViewModel(homeBinding) // Setup ViewModel

    }

    private fun initToolbar(homeBinding: ActivityMainBinding) {
        setSupportActionBar(homeBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = ""
    }

    private fun initViewModel(homeBinding: ActivityMainBinding) {
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]

        //Movies
        val movies = viewModel.getMovies()
        initPropertyMovies(movies, homeBinding)


        //TVShow
        val tvShow = viewModel.getTVShow()
        initPropertyTVShow(tvShow, homeBinding)
    }

    private fun initPropertyMovies(movies: List<DataEntity>, homeBinding: ActivityMainBinding) {
        val adapter = MoviesAdapter()
        adapter.setMovies(movies)

        with(homeBinding.detailContentHomePopular.rvResultsMovie) {
            layoutManager =
                GridLayoutManager(context, 2)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun initPropertyTVShow(tvShow: List<FeaturedEntity>, homeBinding: ActivityMainBinding) {
        val adapters = ImageSliderAdapter(tvShow, this)
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
