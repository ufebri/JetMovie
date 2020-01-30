package com.bedboy.jetmovie.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.model.entity.Results
import com.bedboy.jetmovie.ui.detail.DetailActivity
import com.bedboy.jetmovie.ui.detail.DetailActivity.Companion.DATA_RESULT
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_home.view.*

class MoviesAdapter(private val resultItem: ArrayList<Results>, val context: Context?) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)
        )
    }

    override fun getItemCount(): Int = resultItem.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(resultItem[position])
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(result: Results) {
            itemView.tv_titleFilm_item_home.text = result.title
            itemView.tv_releaseFilm_item_home.text = result.year

            Glide.with(itemView.context)
                .load(result.poster)
                .into(itemView.iv_posterFilm_item_home)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DATA_RESULT, result)
                itemView.context.startActivity(intent)
            }
        }

    }

    fun loadNextPage(result: List<Results>) {
        resultItem.clear()
        this.resultItem.addAll(result)
        notifyItemRangeChanged(0, this.resultItem.size)
        notifyDataSetChanged()
    }

}
