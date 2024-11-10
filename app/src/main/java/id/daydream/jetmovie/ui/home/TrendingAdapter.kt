package id.daydream.jetmovie.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.daydream.jetmovie.BuildConfig
import id.daydream.jetmovie.R
import id.daydream.jetmovie.data.source.local.entity.DataMovieTVEntity
import id.daydream.jetmovie.databinding.ItemImageSliderHomeBinding
import id.daydream.jetmovie.ui.detail.DetailActivity
import id.daydream.jetmovie.ui.detail.DetailActivity.Companion.DATA_RESULT

class TrendingAdapter :
    PagedListAdapter<DataMovieTVEntity, TrendingAdapter.TrendingViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataMovieTVEntity>() {
            override fun areItemsTheSame(
                oldItem: DataMovieTVEntity,
                newItem: DataMovieTVEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DataMovieTVEntity,
                newItem: DataMovieTVEntity
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

                tvTitleFeatured.text = trending.name ?: trending.title
                tvRatingFeatured.text = trending.vote.toString()
                tvGenreFeatured.text = trending.genre?.split(",")?.first()
                rbRatingFeatured.rating = trending.vote!!.toFloat()

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