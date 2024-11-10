package id.daydream.jetmovie.ui.home

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
import id.daydream.jetmovie.R
import id.daydream.jetmovie.data.source.local.entity.DataMovieTVEntity
import id.daydream.jetmovie.databinding.ContentHomePopularBinding
import id.daydream.jetmovie.utils.ViewModelFactory
import id.daydream.jetmovie.vo.Resource
import id.daydream.jetmovie.vo.Status

class PopularFragment : Fragment() {

    private var _popularBinding: ContentHomePopularBinding? = null
    private val binding get() = _popularBinding
    private lateinit var popularAdapter: MoviesAdapter
    private lateinit var trendingAdapter: TrendingAdapter
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

            popularAdapter = MoviesAdapter()
            trendingAdapter = TrendingAdapter()

            showLoading(true)
            viewModel.trending().observe(viewLifecycleOwner, trendingObserver)
            viewModel.popular().observe(viewLifecycleOwner, popularObserver)
            showTrending()
            showPopular()
        }
    }

    private val trendingObserver = Observer<Resource<PagedList<DataMovieTVEntity>>> { result ->
        if (result != null) {
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
                        trendingAdapter.submitList(result.data)
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

    private fun showTrending() {
        binding?.let {
            with(it.rvResultTrending) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                val snapHelper = LinearSnapHelper()
                snapHelper.attachToRecyclerView(it.rvResultTrending)
                adapter = trendingAdapter
            }
        }
    }

    private val popularObserver = Observer<Resource<PagedList<DataMovieTVEntity>>> { result ->
        if (result != null) {
            when (result.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    popularAdapter.submitList(result.data)
                }

                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(context, "Trending: Failed to get Data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showPopular() {
        binding?.let {
            with(it.rvResultsMovie) {
                layoutManager =
                    GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = popularAdapter
            }

            it.tvPopularHome.text =
                getString(R.string.popular)
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