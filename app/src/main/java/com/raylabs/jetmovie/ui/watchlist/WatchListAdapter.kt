package com.raylabs.jetmovie.ui.watchlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raylabs.jetmovie.BuildConfig
import com.raylabs.jetmovie.R
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.databinding.ItemWatchlistBinding
import com.raylabs.jetmovie.ui.detail.DetailActivity
import com.raylabs.jetmovie.utils.createDiffCallback

class WatchListAdapter :
    PagedListAdapter<DataMovieTVEntity, WatchListAdapter.MoviesViewHolder>(
        createDiffCallback<DataMovieTVEntity>(
            idSelector = { it.id },
            contentEquality = { old, new -> old == new }
        )
    ) {

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
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailActivity::class.java)
                        .putExtra(DetailActivity.DATA_RESULT, film)
                )
            }
        }

    }

}