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
import com.raylabs.jetmovie.databinding.ItemHomeBinding

class MoviesAdapter(private val onClick: (Int) -> Unit) :
    PagingDataAdapter<MoviesTV, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemHomeBinding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemHomeBinding)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val popular = getItem(position)
        if (popular != null) {
            holder.bind(popular, onClick)
        }
    }

    class MoviesViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: MoviesTV, onClick: (Int) -> Unit) {

            //glide
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.IMGLINK + film.posterPath)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.ic_no_image)
                    .into(ivPosterFilmItemHome)
            }
            //OnClick
            itemView.setOnClickListener { onClick(film.id) }
        }

    }
}
