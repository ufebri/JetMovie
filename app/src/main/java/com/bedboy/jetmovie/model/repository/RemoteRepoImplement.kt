package com.bedboy.jetmovie.model.repository

import com.bedboy.jetmovie.model.entity.DataDetail
import com.bedboy.jetmovie.model.entity.DataResult
import com.bedboy.jetmovie.network.ApiService
import io.reactivex.Flowable

class RemoteRepoImplement(private val apiService: ApiService) : RemoteRepo {

    override fun getResultsItem(s: String?, page: Int?): Flowable<DataResult> =
        apiService.getResultsSearch(s, page)

    override fun getDetailItem(id: String?): Flowable<DataDetail> = apiService.getDetailItem(id)

}