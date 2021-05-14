package com.bedboy.jetmovie.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bedboy.jetmovie.BuildConfig
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.ui.detail.DetailActivity
import com.bedboy.jetmovie.ui.detail.DetailActivity.Companion.DATA_RESULT
import com.bumptech.glide.Glide

class ImageSliderAdapter(private var list: List<ResultsItem>, private var ctx: Context) :
    PagerAdapter() {


    private lateinit var layoutInflater: LayoutInflater
    private lateinit var indicator: LinearLayout

    override fun getCount(): Int {
        return list.size
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(R.layout.item_image_slider_home, container, false)
        val img = view.findViewById<ImageView>(R.id.iv_slide_home)
        val title = view.findViewById<TextView>(R.id.tv_titleFeatured)
        val vote = view.findViewById<TextView>(R.id.tv_ratingFeatured)
        val voteBar = view.findViewById<RatingBar>(R.id.rb_ratingFeatured)
        val genre = view.findViewById<TextView>(R.id.tv_genreFeatured)
        indicator = view.findViewById(R.id.ll_slide_home)


        title.text = list[position].name ?: list[position].title
        vote.text = list[position].voteAverage.toString()
        voteBar.rating = list[position].voteAverage.toFloat()
        genre.text = list[position].genreIds[0].toString()

        with(view) {
            Glide.with(context)
                .load(BuildConfig.IMGLINK + list[position].backdropPath)
                .into(img)
        }

        view.setOnClickListener {
            view.context.startActivity(
                Intent(view.context, DetailActivity::class.java).putExtra(
                    DATA_RESULT,
                    list[position].id
                )
            )
        }

        val vp = container as ViewPager
        vp.addView(view, 0)

        addPageIndicator()

        return view
    }

    private fun addPageIndicator() {
        indicator.removeAllViews()
        for (i in list.indices) {
            val view = ImageView(ctx)
            view.setImageResource(R.drawable.inactive_dot_slider)

            indicator.addView(view)
        }
        updatePageIndicator(0)
    }

    fun updatePageIndicator(position: Int) {
        var imageView: ImageView
        val lp =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        lp.setMargins(5, 0, 5, 0)
        for (i in 0 until indicator.childCount) {
            imageView = indicator.getChildAt(i) as ImageView
            imageView.layoutParams = lp
            if (position == i) {
                imageView.setImageResource(R.drawable.active_dot_slider)
            } else {
                imageView.setImageResource(R.drawable.inactive_dot_slider)
            }
        }
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }


}