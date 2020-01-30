package com.bedboy.jetmovie.ui.home

import com.bedboy.jetmovie.model.entity.Results

interface HomeContract {

    interface View {
        fun showLoading()
        fun showResultItem(resultsItem: ArrayList<Results>)
        fun hideLoading()
    }

    interface Presenter {
        fun getResultItem(query: String?, pages: Int?)
        fun onDestroyPresenter()
    }
}