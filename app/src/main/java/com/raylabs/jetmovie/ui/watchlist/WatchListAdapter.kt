package com.raylabs.jetmovie.ui.watchlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raylabs.jetmovie.BuildConfig
import com.raylabs.jetmovie.R
import com.raylabs.jetmovie.core.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.databinding.ItemWatchlistBinding
import com.raylabs.jetmovie.ui.detail.DetailActivity
import com.bumptech.glide.Glide

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
                Glide.with(itemView.context)
                    .load(BuildConfig.IMGLINK + film.backDropPath)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.ic_no_image)
                    .into(ivPosterWatchList)
            }

            //OnClick
            itemView.setOnClickListener {}
        }

    }

}