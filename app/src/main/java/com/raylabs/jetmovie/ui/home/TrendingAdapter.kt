package com.raylabs.jetmovie.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raylabs.jetmovie.BuildConfig
import com.raylabs.jetmovie.R
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.databinding.ItemImageSliderHomeBinding
import com.raylabs.jetmovie.ui.detail.DetailActivity
import com.raylabs.jetmovie.ui.detail.DetailActivity.Companion.DATA_RESULT
import com.raylabs.jetmovie.ui.home.TrendingAdapter.TrendingViewHolder
import com.raylabs.jetmovie.utils.createDiffCallback
import java.util.Locale

class TrendingAdapter :
    PagedListAdapter<DataMovieTVEntity, TrendingViewHolder>(
        createDiffCallback<DataMovieTVEntity>(
            idSelector = { it.id },
            contentEquality = { old, new -> old == new }
        )
    ) {

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
            holder.bind(trending)
        }
    }

    inner class TrendingViewHolder(private val binding: ItemImageSliderHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trending: DataMovieTVEntity) {
            with(binding) {

                Glide.with(itemView.context)
                    .load(BuildConfig.IMGLINK.plus(trending.backDropPath))
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.ic_no_image)
                    .into(ivSlideHome)

                //Convert trending to double with  2 digit only
                val mVote: Double = (trending.vote ?: 0.0) / 10.0 * 5.0

                tvTitleFeatured.text = trending.title
                tvRatingFeatured.text = String.format(Locale.getDefault(), "%.1f", trending.vote)
                tvGenreFeatured.text = trending.genre?.split(",")?.first()
                rbRatingFeatured.rating =
                    String.format(Locale.getDefault(), "%.1f", mVote).toFloat()

                //Only show for upcoming type
                tvReleasedDate.apply {
                    isGone = !trending.dataFrom.equals("upcoming")
                    text = trending.releaseData.toString()
                }


                itemView.setOnClickListener {
                    itemView.context.startActivity(
                        Intent(itemView.context, DetailActivity::class.java)
                            .putExtra(DATA_RESULT, trending)
                    )
                }
            }
        }
    }
}