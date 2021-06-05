package com.bedboy.jetmovie.ui.watchlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bedboy.jetmovie.BuildConfig
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.databinding.ItemWatchlistBinding
import com.bedboy.jetmovie.ui.detail.DetailActivity
import com.bedboy.jetmovie.utils.GlideApp

class WatchListAdapter :
    PagedListAdapter<DataMovieTVEntity, WatchListAdapter.MoviesViewHolder>(DIFF_CALLBACK) {


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

    fun getSwipedData(swipedPosition: Int): DataMovieTVEntity? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemHomeBinding =
            ItemWatchlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemHomeBinding)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val watchList = getItem(position)
        if (watchList != null) {
            holder.bind(watchList)
        }
    }

    class MoviesViewHolder(private val binding: ItemWatchlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: DataMovieTVEntity) {

            //glide
            with(binding) {
                GlideApp.with(itemView.context)
                    .load(BuildConfig.IMGLINK + film.backDropPath)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.ic_no_image)
                    .into(ivPosterWatchList)
            }

            //OnClick
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailActivity::class.java)
                        .putExtra(DetailActivity.DATA_RESULT, film)
                )
            }
        }

    }

}