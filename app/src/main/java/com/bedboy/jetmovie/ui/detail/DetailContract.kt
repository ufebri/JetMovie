package com.bedboy.jetmovie.ui.detail

import com.bedboy.jetmovie.model.entity.DataDetail

interface DetailContract {

    interface View {
        fun showLoading()
        fun setItemProperty(resultItem: DataDetail)
        fun hideLoading()
        fun showMessage(message: String?)
    }

    interface Presenter {
        fun getResultItem(idIMDB: String?)
        fun onDestroyPresenter()
    }
}