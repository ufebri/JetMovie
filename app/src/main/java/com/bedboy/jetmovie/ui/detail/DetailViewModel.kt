package com.bedboy.jetmovie.ui.detail

import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.source.local.entity.DetailDataEntity
import com.bedboy.jetmovie.utils.DataDummy

class DetailViewModel : ViewModel() {

    private lateinit var dataID: String

    fun setSelectedData(id: String) {
        this.dataID = id
    }

    private fun getDetailData(): List<DetailDataEntity> = DataDummy.generateDetailData(dataID)

    fun getSelectedData(): DetailDataEntity {
        lateinit var data: DetailDataEntity
        val dataEntities = getDetailData()
        for (dataEntity in dataEntities) {
            if (dataEntity.id == dataID) {
                data = dataEntity
            }
        }
        return data
    }
}