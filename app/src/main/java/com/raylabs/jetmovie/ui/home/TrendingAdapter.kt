package com.raylabs.jetmovie.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raylabs.jetmovie.BuildConfig
import com.raylabs.jetmovie.R
import com.raylabs.jetmovie.core.domain.model.MoviesTV
import com.raylabs.jetmovie.databinding.ItemImageSliderHomeBinding
import com.raylabs.jetmovie.ui.home.TrendingAdapter.TrendingViewHolder

class TrendingAdapter(private val onClick: (Int) -> Unit) :
    PagingDataAdapter<MoviesTV, TrendingViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesTV>() {
            override fun areItemsTheSame(
                oldItem: MoviesTV,
                newItem: MoviesTV
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MoviesTV,
                newItem: MoviesTV
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingViewHolder {
        val itemHomeBinding =
            ItemImageSliderHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(itemHomeBinding)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val trending = getItem(position)
        if (trending != null) {
            holder.bind(trending, onClick)
        }
    }

    inner class TrendingViewHolder(private val binding: ItemImageSliderHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trending: MoviesTV, onClick: (Int) -> Unit) {
            with(binding) {

                Glide.with(itemView.context)
                    .load(BuildConfig.IMGLINK.plus(trending.backdropPath))
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.ic_no_image)
                    .into(ivSlideHome)

                tvTitleFeatured.text = trending.title
                tvRatingFeatured.text = trending.rating
                tvGenreFeatured.text = trending.genre.split(",").first()
                rbRatingFeatured.rating = trending.ratingBar

                itemView.setOnClickListener { onClick(trending.id) }
            }
        }
    }
}