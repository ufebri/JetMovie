package com.raylabs.jetmovie.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.databinding.ContentHomeUpcomingBinding
import com.raylabs.jetmovie.ui.home.TrendingAdapter
import com.raylabs.jetmovie.utils.ViewModelFactory
import com.raylabs.jetmovie.vo.Resource
import com.raylabs.jetmovie.vo.Status

class UpcomingFragment : Fragment() {

    private var upComingBinding: ContentHomeUpcomingBinding? = null
    private val binding get() = upComingBinding
    private lateinit var viewModel: UpcomingViewModel


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
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[UpcomingViewModel::class.java]

            viewModel.getAllUpcoming().observe(viewLifecycleOwner, upcomingObserver)
        }
    }

    private val upcomingObserver = Observer<Resource<PagedList<DataMovieTVEntity>>> { result ->
        when (result.status) {
            Status.LOADING -> showLoading(result.data?.size != 0)
            Status.SUCCESS -> showRecyclerView(result.data)
            Status.ERROR -> {
                showLoading(false)
                showNoData()
            }
        }
    }

    private fun showNoData() {
        binding?.apply {
            ivUpcomingNoData.isVisible = true
            tvNoData.isVisible = true
        }
    }

    private fun showRecyclerView(data: PagedList<DataMovieTVEntity>?) {
        binding?.let {
            with(it.rvUpcoming) {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                setHasFixedSize(true)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        LinearLayoutManager.VERTICAL
                    )
                )
                val mAdapter = TrendingAdapter()
                adapter = mAdapter
                mAdapter.submitList(data)
                showLoading(false)
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.let {
                with(it.shimmerWatchList) {
                    isVisible = true
                    isShimmerVisible
                    startShimmer()
                }
            }
        } else {
            binding?.let {
                with(it) {
                    shimmerWatchList.stopShimmer()
                    shimmerWatchList.isGone = true
                    rvUpcoming.isVisible = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        upComingBinding = null
    }
}