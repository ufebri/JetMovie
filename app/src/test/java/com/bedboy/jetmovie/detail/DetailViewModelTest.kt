package com.bedboy.jetmovie.detail

import com.bedboy.jetmovie.ui.detail.DetailViewModel
import com.bedboy.jetmovie.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailViewModelTVShow: DetailViewModel
    private val detailDataMovie = DataDummy.generateMovie()[0]
    private val detailDataTVShow = DataDummy.generateTVShow()[0]
    private val idMovie = detailDataMovie.id
    private val idTVShow = detailDataTVShow.id

    @Before
    fun setUpMovie() {
        detailViewModel = DetailViewModel()
        detailViewModel.setSelectedData(idMovie)
    }


    @Test
    fun getDetailMovie() {
        val detail = detailViewModel.getSelectedData()
        assertNotNull(detail)
        assertEquals(detailDataMovie.id, detail.id)
    }

    @Before
    fun setUpTVShow() {
        detailViewModelTVShow = DetailViewModel()
        detailViewModelTVShow.setSelectedData(idTVShow)
    }

    @Test
    fun getDetailTVShow() {
        val detailTVShow = detailViewModelTVShow.getSelectedData()
        assertNotNull(detailTVShow)
        assertEquals(detailDataTVShow.id, detailTVShow.id)
    }
}