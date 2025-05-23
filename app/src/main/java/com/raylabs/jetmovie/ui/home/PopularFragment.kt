package com.raylabs.jetmovie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.raylabs.jetmovie.R
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.databinding.ContentHomePopularBinding
import com.raylabs.jetmovie.utils.ViewModelFactory
import com.raylabs.jetmovie.vo.Resource
import com.raylabs.jetmovie.vo.Status

class PopularFragment : Fragment() {

    private var _popularBinding: ContentHomePopularBinding? = null
    private val binding get() = _popularBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _popularBinding = ContentHomePopularBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            showLoading(true)
            viewModel.trending().observe(viewLifecycleOwner, trendingObserver)
            viewModel.popular().observe(viewLifecycleOwner, popularObserver)

            binding?.apply {
                svHome.setupWithSearchBar(sbHome)
                svHome.editText.setOnEditorActionListener { _, _, _ ->
                    sbHome.setText(svHome.text)
                    svHome.hide()
                    viewModel.getMovieByKeyword(svHome.text.toString())
                        .observe(viewLifecycleOwner, getMovieByKeywordObserver)
                    false
                }
            }
        }
    }

    private val getMovieByKeywordObserver =
        Observer<Resource<PagedList<DataMovieTVEntity>>> { result ->
            when (result.status) {
                Status.LOADING -> {
                    if (result.data != null) {
                        showLoading(true)
                        showNoConnection(false)
                    } else {
                        showLoading(false)
                    }
                }

                Status.SUCCESS -> {
                    showLoading(false)
                    if (result.data != null) showPopular(result.data)
                }

                Status.ERROR -> {
                    showLoading(false)
                    showNoConnection(true)
                    Toast.makeText(context, "Popular: Failed to get Data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    private val trendingObserver = Observer<Resource<PagedList<DataMovieTVEntity>>> { result ->
        when (result.status) {
            Status.LOADING -> {
                if (result.data?.size != 0) {
                    showLoading(true)
                    showNoConnection(false)
                } else {
                    showLoading(false)
                }
            }

            Status.SUCCESS -> {
                if (result.data != null) {
                    showTrending(result.data)
                    showLoading(false)
                }
            }

            Status.ERROR -> {
                showLoading(false)
                showNoConnection(true)
                Toast.makeText(context, "Popular: Failed to get Data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun showNoConnection(state: Boolean) {
        if (state)
            binding?.apply {
                itemNoConnection.tvNoConnectionHome.isVisible = true
                itemNoConnection.ivNoConnection.isVisible = true
                tvPopularHome.isGone = true
                itemShimmer.containerShimmerHome.isGone = true

            }
        else
            binding?.apply {
                itemNoConnection.tvNoConnectionHome.isGone = true
                itemNoConnection.ivNoConnection.isGone = true
            }
    }

    private fun showTrending(mData: PagedList<DataMovieTVEntity>) {
        binding?.let {
            with(it.rvResultTrending) {
                val trendingAdapter = TrendingAdapter()
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                val snapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(it.rvResultTrending)
                adapter = trendingAdapter
                trendingAdapter.submitList(mData)
            }
        }
    }

    private val popularObserver = Observer<Resource<PagedList<DataMovieTVEntity>>> { result ->
        when (result.status) {
            Status.LOADING -> showLoading(true)
            Status.SUCCESS -> {
                showLoading(false)
                if (result.data != null) showPopular(result.data)
            }

            Status.ERROR -> {
                showLoading(false)
                Toast.makeText(context, "Trending: Failed to get Data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun showPopular(mData: PagedList<DataMovieTVEntity>) {
        binding?.let {
            with(it.rvResultsMovie) {
                val popularAdapter = MoviesAdapter()
                layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                adapter = popularAdapter
                popularAdapter.submitList(mData)
            }

            it.tvPopularHome.text =
                getString(R.string.popular)

            showBannerAds()
        }
    }

    private fun showBannerAds() {
        binding?.apply {
            startAppBanner.loadAd()
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.apply {
                rvResultsMovie.isGone = true
                tvPopularHome.isGone = true
                rvResultTrending.isGone = true

                shimmerHome.isVisible = true
                shimmerHome.startShimmer()
            }
        } else {
            binding?.apply {
                shimmerHome.stopShimmer()
                shimmerHome.hideShimmer()
                shimmerHome.isGone = true

                rvResultTrending.isVisible = true
                rvResultsMovie.isVisible = true
                tvPopularHome.isVisible = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _popularBinding?.shimmerHome?.stopShimmer()
        _popularBinding = null
    }

}