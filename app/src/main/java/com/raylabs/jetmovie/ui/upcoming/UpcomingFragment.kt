package com.raylabs.jetmovie.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raylabs.jetmovie.databinding.ContentHomeUpcomingBinding
import com.raylabs.jetmovie.ui.adapter.LoadingStateAdapter
import com.raylabs.jetmovie.ui.home.TrendingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpcomingFragment : Fragment() {

    private var upComingBinding: ContentHomeUpcomingBinding? = null
    private val binding get() = upComingBinding
    private val viewModel: UpcomingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        upComingBinding = ContentHomeUpcomingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showLoading(true)
            val mAdapter = TrendingAdapter {}

            binding?.rvUpcoming?.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        LinearLayoutManager.VERTICAL
                    )
                )

                adapter = mAdapter.withLoadStateFooter(footer = LoadingStateAdapter {
                    mAdapter.retry()
                })
            }

            lifecycleScope.launch {
                viewModel.upcoming.collectLatest { result ->
                    mAdapter.submitData(result)
                }
            }

            showLoading(false)
        }
    }

    private fun showNoData() {
        binding?.apply {
            ivUpcomingNoData.isVisible = true
            tvNoData.isVisible = true
        }
    }

    private fun showLoading(state: Boolean) {
        binding?.apply {
            if (state) {
                shimmerWatchList.isVisible = true
                shimmerWatchList.isShimmerVisible
                shimmerWatchList.startShimmer()
            } else {
                shimmerWatchList.stopShimmer()
                shimmerWatchList.isGone = true
                rvUpcoming.isVisible = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        upComingBinding?.shimmerWatchList?.stopShimmer()
        upComingBinding = null
    }
}