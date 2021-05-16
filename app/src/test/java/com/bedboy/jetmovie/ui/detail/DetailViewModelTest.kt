package com.bedboy.jetmovie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity
import com.bedboy.jetmovie.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyGenre = DataDummy.generateGenre()
    private val dummyVideo = DataDummy.generateVideo()

    private val idMovie = DataDummy.generateData()[0].id
    private val idTVShow = DataDummy.generateData()[13].id
    private val mediaTypeMovie = DataDummy.generateData()[0].media_type
    private val mediaTypeTVShow = DataDummy.generateData()[13].media_type

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observerGenre: Observer<List<GenreEntity>>

    @Mock
    private lateinit var observerVideo: Observer<List<VideoEntity>>


    @Before
    fun setup() {
        viewModel = DetailViewModel(dataRepository)
    }


    @Test
    fun getGenreDetailMovie() {
        val genre = MutableLiveData<List<GenreEntity>>()
        genre.value = dummyGenre

        `when`(dataRepository.getGenre(mediaTypeMovie)).thenReturn(genre)
        val genreEntity = viewModel.getGenre(mediaTypeMovie).value
        verify(dataRepository).getGenre(mediaTypeMovie)

        assertNotNull(genreEntity)
        assertEquals(5, dummyGenre.size)

        viewModel.getGenre(mediaTypeMovie).observeForever(observerGenre)
        verify(observerGenre).onChanged(dummyGenre)
    }

    @Test
    fun getVideoDetailMovie() {
        val video = MutableLiveData<List<VideoEntity>>()
        video.value = dummyVideo

        `when`(dataRepository.getVideoDetail(mediaTypeMovie, idMovie)).thenReturn(video)
        val videoEntity = viewModel.getvideos(mediaTypeMovie, idMovie).value
        verify(dataRepository).getVideoDetail(mediaTypeMovie, idMovie)

        assertNotNull(videoEntity)
        assertEquals(20, dummyVideo.size)

        viewModel.getvideos(mediaTypeMovie, idMovie).observeForever(observerVideo)
        verify(observerVideo).onChanged(dummyVideo)
    }

    @Test
    fun getGenreTVShow() {
        val genre = MutableLiveData<List<GenreEntity>>()
        genre.value = dummyGenre

        `when`(dataRepository.getGenre(mediaTypeTVShow)).thenReturn(genre)
        val genreEntity = viewModel.getGenre(mediaTypeTVShow).value
        verify(dataRepository).getGenre(mediaTypeTVShow)

        assertNotNull(genreEntity)
        assertEquals(5, dummyGenre.size)

        viewModel.getGenre(mediaTypeTVShow).observeForever(observerGenre)
        verify(observerGenre).onChanged(dummyGenre)
    }

    @Test
    fun getVideoDetailTVShow() {
        val video = MutableLiveData<List<VideoEntity>>()
        video.value = dummyVideo

        `when`(
            dataRepository.getVideoDetail(
                mediaTypeTVShow,
                idTVShow
            )
        ).thenReturn(video)
        val videoEntity = viewModel.getvideos(mediaTypeTVShow, idTVShow).value
        verify(dataRepository).getVideoDetail(mediaTypeTVShow, idTVShow)

        assertNotNull(videoEntity)
        assertEquals(20, dummyVideo.size)

        viewModel.getvideos(mediaTypeTVShow, idTVShow).observeForever(observerVideo)
        verify(observerVideo).onChanged(dummyVideo)
    }
}