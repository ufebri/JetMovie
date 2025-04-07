package com.raylabs.jetmovie.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.raylabs.jetmovie.databinding.ContentHomePopularBinding
import com.raylabs.jetmovie.ui.adapter.LoadingStateAdapter
import com.raylabs.jetmovie.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private var _popularBinding: ContentHomePopularBinding? = null
    private val binding get() = _popularBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _popularBinding = ContentHomePopularBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            // showLoading(true)

            val mAdapter = MoviesAdapter { idItem -> goToDetail(idItem) }
            binding?.rvResultsMovie?.layoutManager = GridLayoutManager(context, 2)
            binding?.rvResultsMovie?.adapter = mAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter { mAdapter.retry() }
            )

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.popular.collectLatest { result ->
                    mAdapter.submitData(result)
                }
            }

//            binding?.let {
//                with(it.rvResultTrending) {
//                    val trendingAdapter = TrendingAdapter { idItem -> goToDetail(idItem) }
//                    layoutManager =
//                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//                    val snapHelper = LinearSnapHelper()
//                    snapHelper.attachToRecyclerView(this)
//                    adapter = trendingAdapter.withLoadStateHeaderAndFooter(
//                        footer = LoadingStateAdapter { trendingAdapter.retry() },
//                        header = LoadingStateAdapter { trendingAdapter.retry() }
//                    )
//
//                    lifecycleScope.launch {
//                        viewModel.trending.collectLatest {
//                            trendingAdapter.submitData(it)
//                        }
//                    }
//                }
//
//                it.svHome.setupWithSearchBar(it.sbHome)
//                it.svHome.editText.setOnEditorActionListener { _, _, _ ->
//                    it.sbHome.setText(it.svHome.text)
//                    it.svHome.hide()
//                    false
//                }
//            }

            binding?.apply {
                //rvResultTrending.isVisible = true
                rvResultsMovie.isVisible = true
                // tvPopularHome.isVisible = true
                showNoConnection(false)
            }
        }
    }

    private fun showNoConnection(state: Boolean) {
        binding?.apply {
            if (state) {
                itemNoConnection.tvNoConnectionHome.isVisible = true
                itemNoConnection.ivNoConnection.isVisible = true
                //tvPopularHome.isGone = true
                itemShimmer.containerShimmerHome.isGone = true
            } else {
                itemNoConnection.tvNoConnectionHome.isGone = true
                itemNoConnection.ivNoConnection.isGone = true
            }
        }
    }

    private fun goToDetail(id: Int) =
        startActivity(Intent(requireActivity(), DetailActivity::class.java).putExtra("id", id))

    private fun showLoading(state: Boolean) {
        binding?.apply {
            if (state) {
                rvResultsMovie.isGone = true
                //tvPopularHome.isGone = true
                //rvResultTrending.isGone = true

                shimmerHome.isVisible = true
                shimmerHome.startShimmer()
            } else {
                shimmerHome.stopShimmer()
                shimmerHome.hideShimmer()
                shimmerHome.isGone = true

                //rvResultTrending.isVisible = true
                rvResultsMovie.isVisible = true
                //tvPopularHome.isVisible = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _popularBinding?.shimmerHome?.stopShimmer()
        _popularBinding = null
    }

}