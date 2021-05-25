package com.bedboy.jetmovie.ui.watchlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.databinding.ActivityWatchListBinding
import com.bedboy.jetmovie.utils.ViewModelFactory

class WatchListActivity : AppCompatActivity() {

    private lateinit var watchListBinding: ActivityWatchListBinding
    private lateinit var viewModel: WatchListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[WatchListViewModel::class.java]

        watchListBinding = ActivityWatchListBinding.inflate(layoutInflater)
        setContentView(watchListBinding.root)


        initToolbar()
        showLoading(true)
//        itemTouchHelper.attachToRecyclerView(watchListBinding.contentWatchList.rvWatchList)
        showData()
    }

    private fun showData() {
        val watchListAdapter = WatchListAdapter()
        viewModel.getWatchList().observe(this, { result ->
            showLoading(false)
            watchListAdapter.setMovies(result)
        })

        watchListBinding.contentWatchList.rvWatchList.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = watchListAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            watchListBinding.shimmerWatchList.isVisible = true
            watchListBinding.shimmerWatchList.isShimmerVisible
            watchListBinding.shimmerWatchList.startShimmer()
        } else {
            watchListBinding.shimmerWatchList.stopShimmer()
            watchListBinding.shimmerWatchList.isGone = true

            watchListBinding.contentWatchList.rvWatchList.isVisible = true
        }
    }

//    private val itemTouchHelper =
//        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean = true
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val swipedPosition = viewHolder.absoluteAdapterPosition
//                val movieEntity = watchListAdapter.getSwipedData(swipedPosition)
//                movieEntity?.let { viewModel.setFavoriteMovie(it) }
//
//                val snackbar = Snackbar.make(view as View, R.string.message_undo_movie, Snackbar.LENGTH_LONG)
//                snackbar.setAction(R.string.undo) { _ ->
//                    movieEntity?.let { viewModel.setFavoriteMovie(it) }
//                }
//                snackbar.show()
//            }
//
//        }

    private fun initToolbar() {
        setSupportActionBar(watchListBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.watchlist)
    }

    override fun onResume() {
        watchListBinding.shimmerWatchList.startShimmer()
        super.onResume()
    }

    override fun onPause() {
        watchListBinding.shimmerWatchList.stopShimmer()
        super.onPause()
    }
}