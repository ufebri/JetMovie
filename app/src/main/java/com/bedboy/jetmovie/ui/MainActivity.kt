package com.bedboy.jetmovie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.databinding.ActivityMainBinding
import com.bedboy.jetmovie.ui.home.HomeViewModel
import com.bedboy.jetmovie.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        viewModel.genre("tv").observe(this, {})
        viewModel.genre("movie").observe(this, {})
        initToolbar() // Setup Toolbar
    }

    private fun initToolbar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_upcoming,
                R.id.navigation_watchlist,
                R.id.navigation_profile
            )
        )
        setSupportActionBar(homeBinding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        homeBinding.navViewHome.setupWithNavController(navController)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = ""
    }
}
