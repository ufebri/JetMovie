package com.raylabs.jetmovie.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raylabs.jetmovie.BuildConfig
import com.raylabs.jetmovie.R
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.databinding.ItemHomeBinding
import com.raylabs.jetmovie.ui.detail.DetailActivity
import com.raylabs.jetmovie.ui.detail.DetailActivity.Companion.DATA_RESULT
import com.raylabs.jetmovie.utils.createDiffCallback

class MoviesAdapter :
    PagedListAdapter<DataMovieTVEntity, MoviesAdapter.MoviesViewHolder>(
        createDiffCallback<DataMovieTVEntity>(
            idSelector = { it.id },
            contentEquality = { old, new -> old == new }
        )
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemHomeBinding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemHomeBinding)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val popular = getItem(position)
        if (popular != null) {
            holder.bind(popular)
        }
    }

    class MoviesViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: DataMovieTVEntity) {

            //glide
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.IMGLINK + film.imagePath)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.ic_no_image)
                    .into(ivPosterFilmItemHome)
            }

            //OnClick
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailActivity::class.java)
                        .putExtra(DATA_RESULT, film)
                )
            }
        }

    }
}
