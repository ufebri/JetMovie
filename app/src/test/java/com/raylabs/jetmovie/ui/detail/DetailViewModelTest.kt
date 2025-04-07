package com.raylabs.jetmovie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.raylabs.jetmovie.core.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.core.data.source.local.entity.VideoEntity
import com.raylabs.jetmovie.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
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
    private val dummyVideo = Resource.success(DataDummy.generateVideo())
    private val dummyDetailMovie = Resource.success(DataDummy.generateDetailDataMovie())
    private val dummyDetailTVShow = Resource.success(DataDummy.generateDetailDataTVShow())


    private val idMovie = DataDummy.generateData()[0].id
    private val idTVShow = DataDummy.generateData()[13].id
    private val mediaTypeMovie = DataDummy.generateData()[0].media_type
    private val mediaTypeTVShow = DataDummy.generateData()[13].media_type

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observerVideo: Observer<Resource<List<VideoEntity>>>

    @Mock
    private lateinit var observerDetail: Observer<Resource<DataMovieTVEntity>>

    @Before
    fun setup() {
        viewModel = DetailViewModel(dataRepository)
    }


    @Test
    fun getVideoDetailMovie() {
        val video = MutableLiveData<Resource<List<VideoEntity>>>()
        video.value = dummyVideo

        `when`(dataRepository.getVideoDetail(mediaTypeMovie.toString(), idMovie)).thenReturn(video)
        val videoEntity = viewModel.getVideos(mediaTypeMovie.toString(), idMovie).value
        verify(dataRepository).getVideoDetail(mediaTypeMovie.toString(), idMovie)

        assertNotNull(videoEntity)
        assertEquals(20, dummyVideo.data?.size)

        viewModel.getVideos(mediaTypeMovie.toString(), idMovie).observeForever(observerVideo)
        verify(observerVideo).onChanged(dummyVideo)
    }

    @Test
    fun getVideoDetailTVShow() {
        val video = MutableLiveData<Resource<List<VideoEntity>>>()
        video.value = dummyVideo

        `when`(
            dataRepository.getVideoDetail(
                mediaTypeTVShow.toString(),
                idTVShow
            )
        ).thenReturn(video)
        val videoEntity = viewModel.getVideos(mediaTypeTVShow.toString(), idTVShow).value
        verify(dataRepository).getVideoDetail(mediaTypeTVShow.toString(), idTVShow)

        assertNotNull(videoEntity)
        assertEquals(20, dummyVideo.data?.size)

        viewModel.getVideos(mediaTypeTVShow.toString(), idTVShow).observeForever(observerVideo)
        verify(observerVideo).onChanged(dummyVideo)
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = MutableLiveData<Resource<DataMovieTVEntity>>()
        detailMovie.value = dummyDetailMovie

        `when`(dataRepository.getDetailMovie(idMovie)).thenReturn(
            detailMovie
        )

        viewModel.selectedData(idMovie)
        viewModel.getDetailMovie.observeForever(observerDetail)
        verify(observerDetail).onChanged(dummyDetailMovie)
    }

    @Test
    fun getDetailTVShow() {
        val detailTVShow = MutableLiveData<Resource<DataMovieTVEntity>>()
        detailTVShow.value = dummyDetailTVShow

        `when`(dataRepository.getDetailTV(idTVShow)).thenReturn(
            detailTVShow
        )

        viewModel.selectedData(idTVShow)
        viewModel.getDetailTV.observeForever(observerDetail)
        verify(observerDetail).onChanged(dummyDetailTVShow)
    }

    @Test
    fun setWatchListMovie() {
        val movie = MutableLiveData<Resource<DataMovieTVEntity>>()
        movie.value = dummyDetailMovie

        `when`(dataRepository.getDetailMovie(idMovie)).thenReturn(movie)
        viewModel.getDetailMovie = dataRepository.getDetailMovie(idMovie)
        viewModel.addToWatchList()
        verify(dataRepository).setWatchList(movie.value?.data as DataMovieTVEntity, true)
        verifyNoMoreInteractions(observerDetail)
    }

    @Test
    fun setWatchListTVShow() {
        val tv = MutableLiveData<Resource<DataMovieTVEntity>>()
        tv.value = dummyDetailTVShow

        `when`(dataRepository.getDetailTV(idTVShow)).thenReturn(tv)
        viewModel.getDetailTV = dataRepository.getDetailTV(idTVShow)
        viewModel.addToWatchList()
        verify(dataRepository).setWatchList(tv.value?.data as DataMovieTVEntity, true)
        verifyNoMoreInteractions(observerDetail)
    }
}