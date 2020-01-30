package com.bedboy.jetmovie.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.model.entity.Results
import com.bedboy.jetmovie.model.repository.RemoteRepoImplement
import com.bedboy.jetmovie.network.ApiService
import com.bedboy.jetmovie.network.AppSchedulerProvider
import com.bedboy.jetmovie.network.NetworkService
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private var resultsItems: MutableList<Results> = mutableListOf()

    private lateinit var mPresenter: HomePresenter
    private var linearLayoutManager = LinearLayoutManager(this@HomeActivity)
    private var adapterMovie: MoviesAdapter? = null
    private var query: String = "Iron Man"
    private var pages: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNetwork()
    }

    private fun initNetwork() {
        val service = NetworkService.getData().create(ApiService::class.java)
        val request = RemoteRepoImplement(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = HomePresenter(this, request, scheduler)
        mPresenter.getResultItem(query, pages)
    }

    override fun showLoading() {
        pb_home.isVisible = true
    }

    override fun showResultItem(resultsItem: ArrayList<Results>) {
        resultsItems.clear()
        resultsItems.addAll(resultsItem)
        adapterMovie = MoviesAdapter(resultsItem, applicationContext)
        rv_resultsMovie.apply {
            layoutManager = linearLayoutManager
            if (pages == 1) {
                adapter = adapterMovie
            } else {
                adapterMovie?.loadNextPage(resultsItems)
            }
        }

        rv_resultsMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = linearLayoutManager.childCount
                val countItem = linearLayoutManager.itemCount
                val firstVisibleItemCount = linearLayoutManager.findFirstVisibleItemPosition()
                if (firstVisibleItemCount + visibleItemCount >= countItem) {
                    if (pages >= 4) {
                        showMessage(getString(R.string.lastpage))
                        return
                    } else {
                        mPresenter.getResultItem(query, pages++)
                    }
                }
            }
        })
    }

    override fun hideLoading() {
        pb_home.isGone = true
    }

    private fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView?
        searchView?.queryHint = "Find your movies..."
        searchView?.onActionViewExpanded()
        searchView?.maxWidth = Int.MAX_VALUE

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                mPresenter.getResultItem(p0, pages)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                mPresenter.getResultItem(p0, pages)
                return false
            }
        })

        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}
