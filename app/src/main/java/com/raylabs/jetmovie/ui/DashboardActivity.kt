package com.raylabs.jetmovie.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.raylabs.jetmovie.R
import com.raylabs.jetmovie.databinding.ActivityDashboardBinding
import com.raylabs.jetmovie.ui.home.HomeViewModel
import com.raylabs.jetmovie.ui.profile.ThemeViewModel
import com.raylabs.jetmovie.utils.ViewModelFactory
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityDashboardBinding
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory.getInstance(this) }
    private val themeViewModel: ThemeViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Check Dark Theme
        lifecycleScope.launch {
            themeViewModel.isDarkThemeActive.observe(this@DashboardActivity) { isDarkThemeActive ->
                AppCompatDelegate.setDefaultNightMode(
                    if (isDarkThemeActive) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_NO
                    }
                )
            }
        }

        homeBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(homeBinding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, systemBars.top, 0, systemBars.bottom)
            insets
        }

        viewModel.genre("tv").observe(this) {}
        viewModel.genre("movie").observe(this) {}
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
