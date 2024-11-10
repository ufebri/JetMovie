package id.daydream.jetmovie.ui.watchlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.daydream.jetmovie.BuildConfig
import id.daydream.jetmovie.R
import id.daydream.jetmovie.data.source.local.entity.DataMovieTVEntity
import id.daydream.jetmovie.databinding.ItemWatchlistBinding
import id.daydream.jetmovie.ui.detail.DetailActivity
import id.daydream.jetmovie.utils.GlideApp

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