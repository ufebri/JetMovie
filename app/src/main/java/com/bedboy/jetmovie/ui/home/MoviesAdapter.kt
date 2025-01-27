package com.bedboy.jetmovie.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bedboy.jetmovie.BuildConfig
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.databinding.ItemHomeBinding
import com.bedboy.jetmovie.ui.detail.DetailActivity
import com.bedboy.jetmovie.ui.detail.DetailActivity.Companion.DATA_RESULT
import com.bumptech.glide.Glide

class MoviesAdapter :
    PagedListAdapter<DataMovieTVEntity, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

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
                    .centerInside()
                    .override(200, 250)
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
