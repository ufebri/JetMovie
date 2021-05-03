package com.bedboy.jetmovie.detail

import com.bedboy.jetmovie.ui.detail.DetailViewModel
import com.bedboy.jetmovie.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private var detailData = DataDummy.generateMovie()[0]
    private val idMovie = detailData.id

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
        detailViewModel.setSelectedData(idMovie)
    }

    @Test
    fun getDetail() {
        val detail = detailViewModel.getSelectedData()
        assertNotNull(detail)
        assertEquals(detailData.id, detail.id)
    }
}