package com.bedboy.jetmovie.ui.home.popular

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
import androidx.viewpager.widget.ViewPager
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.databinding.ContentHomePopularBinding
import com.bedboy.jetmovie.ui.home.HomeViewModel
import com.bedboy.jetmovie.ui.home.ImageSliderAdapter
import com.bedboy.jetmovie.ui.home.MoviesAdapter
import com.bedboy.jetmovie.utils.ViewModelFactory
import com.bedboy.jetmovie.vo.Resource
import com.bedboy.jetmovie.vo.Status

class PopularFragment : Fragment() {

    private var _popularBinding: ContentHomePopularBinding? = null
    private val binding get() = _popularBinding
    private lateinit var popularAdapter: MoviesAdapter
    private lateinit var trendingAdapter: ImageSliderAdapter
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

            showLoading(true)
            viewModel.popular().observe(viewLifecycleOwner, popularObserver)
            viewModel.trending().observe(viewLifecycleOwner, trendingObserver)
            showRecyclerView()
        }
    }

    private val trendingObserver = Observer<Resource<PagedList<DataMovieTVEntity>>> { result ->
        if (result != null) {
            when (result.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    if (result.data != null) {
                        result.data.let {
                            setGenre(it)
                            showViewPager(it)
                        }
                        showLoading(false)
                    }
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(context, "Popular: Failed to get Data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showViewPager(pagedList: PagedList<DataMovieTVEntity>) {
        trendingAdapter = ImageSliderAdapter(pagedList, requireActivity())
        binding?.let {
            with(it) {
                vpHome.adapter = trendingAdapter
                vpHome.addOnPageChangeListener(object :
                    ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        trendingAdapter.updatePageIndicator(position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {

                    }

                })
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

    private fun showRecyclerView() {
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
                flPopular.isGone = true

                shimmerHome.isVisible = true
                shimmerHome.startShimmer()
            }
        } else {
            binding?.apply {
                shimmerHome.stopShimmer()
                shimmerHome.hideShimmer()
                shimmerHome.isGone = true

                flPopular.isVisible = true
                rvResultsMovie.isVisible = true
                tvPopularHome.isVisible = true
            }
        }
    }

    private fun setGenre(data: PagedList<DataMovieTVEntity>) {
        val listGenre = ArrayList<DataMovieTVEntity>()
        for (response in data) {
            with(response) {
                val genre = DataMovieTVEntity(
                    media_type = media_type,
                    id = id
                )
                listGenre.add(genre)
                genre.media_type?.let { viewModel.selectedData(it) }
            }
        }
        viewModel.genre.observe(viewLifecycleOwner, {})
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _popularBinding?.shimmerHome?.stopShimmer()
        _popularBinding = null
    }

}