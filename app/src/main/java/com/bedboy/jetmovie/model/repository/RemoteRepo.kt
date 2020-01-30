package com.bedboy.jetmovie.model.repository

import com.bedboy.jetmovie.model.entity.DataDetail
import com.bedboy.jetmovie.model.entity.DataResult
import io.reactivex.Flowable

interface RemoteRepo {

    fun getResultsItem(s: String?, page: Int?): Flowable<DataResult>
    fun getDetailItem(id: String?): Flowable<DataDetail>
}