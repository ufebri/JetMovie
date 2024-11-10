package id.daydream.jetmovie.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException
import com.huawei.hms.push.HmsMessaging
import id.daydream.jetmovie.R
import id.daydream.jetmovie.databinding.ActivityMainBinding
import id.daydream.jetmovie.ui.home.HomeViewModel
import id.daydream.jetmovie.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        // Aktifkan menerima pesan push
        HmsMessaging.getInstance(this).isAutoInitEnabled = true

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        viewModel.genre("tv").observe(this, {})
        viewModel.genre("movie").observe(this, {})
        initToolbar() // Setup Toolbar

        getToken()
    }

    private fun getToken() {
        // Harus dilakukan di thread terpisah
        Thread {
            try {
                // `appId` bisa diambil otomatis dari file `agconnect-services.json`
                val appId = "xxxx"
                val token = HmsInstanceId.getInstance(this).getToken(appId, "HCM")
                if (!token.isNullOrEmpty()) {
                    Log.d("PushKit", "Token obtained: $token")
                    // Simpan token ini atau kirim ke server Anda untuk keperluan notifikasi
                }
            } catch (e: ApiException) {
                Log.e("PushKit", "Failed to get token: ${e.message}")
            }
        }.start()
    }

    private fun initToolbar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_watchlist
            )
        )
        setSupportActionBar(homeBinding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        homeBinding.navViewHome.setupWithNavController(navController)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        title = ""
    }
}
