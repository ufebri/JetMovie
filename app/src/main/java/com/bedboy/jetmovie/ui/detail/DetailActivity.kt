package com.bedboy.jetmovie.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.model.entity.DataDetail
import com.bedboy.jetmovie.model.entity.Results
import com.bedboy.jetmovie.model.repository.RemoteRepoImplement
import com.bedboy.jetmovie.network.ApiService
import com.bedboy.jetmovie.network.AppSchedulerProvider
import com.bedboy.jetmovie.network.NetworkService
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var mPresenter: DetailPresenter

    companion object {
        const val DATA_RESULT: String = "data"
        var ID_IMDB: String = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initProperty()
        initNetwork(ID_IMDB)
    }

    private fun initNetwork(idImdb: String) {
        val service = NetworkService.getData().create(ApiService::class.java)
        val request = RemoteRepoImplement(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = DetailPresenter(this, request, scheduler)
        mPresenter.getResultItem(idImdb)
    }

    private fun initProperty() {
        val item = intent.getParcelableExtra<Results>(DATA_RESULT)
        ID_IMDB = item?.imdbID.toString()
    }

    override fun showLoading() {

    }

    override fun setItemProperty(resultItem: DataDetail) {
        Glide.with(this)
            .load(resultItem.poster)
            .error(R.mipmap.ic_launcher)
            .into(iv_posterFilm_detail)
        tv_titleFilm_detail.text = resultItem.title
        tv_categoryFilm_detail.text = StringBuilder(resultItem.genre + "-" + resultItem.runtime)
        tv_releaseFilm_detail.text = resultItem.year.toString()
        tv_descriptionFilm_detail.text = resultItem.plot
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}
