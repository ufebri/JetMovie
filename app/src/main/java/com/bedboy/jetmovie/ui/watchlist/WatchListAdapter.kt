package com.bedboy.jetmovie.ui.watchlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bedboy.jetmovie.BuildConfig
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.databinding.ItemHomeBinding
import com.bedboy.jetmovie.ui.detail.DetailActivity
import com.bumptech.glide.Glide

class WatchListAdapter : RecyclerView.Adapter<WatchListAdapter.MoviesViewHolder>() {

    private var listMovie = ArrayList<DataMovieTVEntity>()

    fun setMovies(movies: List<DataMovieTVEntity>?) {
        if (movies == null) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemHomeBinding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemHomeBinding)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    class MoviesViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: DataMovieTVEntity) {

            //glide
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.IMGLINK + film.imagePath)
                    .into(ivPosterFilmItemHome)
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