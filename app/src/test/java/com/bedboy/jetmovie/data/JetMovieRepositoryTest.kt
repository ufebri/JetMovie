package com.bedboy.jetmovie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bedboy.jetmovie.data.source.remote.RemoteDataSource
import com.bedboy.jetmovie.util.LiveDataTestUtil
import com.bedboy.jetmovie.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class JetMovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val repository = FakeDataRepository(remote)

    private val dataResponseHome = DataDummy.generateRemoteData()
    private val dataResponseGenre = DataDummy.generateRemoteGenre()
    private val dataResponseVideo = DataDummy.generateRemoteVideo()

    private val mediaTypeMovie = dataResponseHome[0].mediaType
    private val mediaTypeTVShow = dataResponseHome[13].mediaType

    private val idMovie = dataResponseHome[0].id
    private val idTVShow = dataResponseHome[13].id


    @Test
    fun getTrending() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadHomeDataCallback)
                .onAllHomeDataReceived(dataResponseHome)
            null
        }.`when`(remote).getAllTrending(any())

        val trendingEntities = LiveDataTestUtil.getValue(repository.getTrending())
        verify(remote).getAllTrending(any())
        Assert.assertNotNull(trendingEntities)
        assertEquals(dataResponseHome.size, trendingEntities.size)
    }

    @Test
    fun getPopular() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadHomeDataCallback)
                .onAllHomeDataReceived(dataResponseHome)
            null
        }.`when`(remote).getAllPopular(any())

        val popularEntities = LiveDataTestUtil.getValue(repository.getPopular())
        verify(remote).getAllPopular(any())
        Assert.assertNotNull(popularEntities)
        assertEquals(dataResponseHome.size, popularEntities.size)
    }

    @Test
    fun getGenreMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadGenreCallback)
                .onAllGenreReceived(dataResponseGenre)
            null
        }.`when`(remote).getAllGenre(
            eq(mediaTypeMovie), any()
        )

        val genreMovie = LiveDataTestUtil.getValue(repository.getGenre(mediaTypeMovie))
        verify(remote).getAllGenre(eq(mediaTypeMovie), any())
        Assert.assertNotNull(genreMovie)
        assertEquals(dataResponseGenre.size, genreMovie.size)
    }

    @Test
    fun getGenreTVShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadGenreCallback)
                .onAllGenreReceived(dataResponseGenre)
            null
        }.`when`(remote).getAllGenre(
            eq(mediaTypeTVShow), any()
        )

        val genreTVShow = LiveDataTestUtil.getValue(repository.getGenre(mediaTypeTVShow))
        verify(remote).getAllGenre(eq(mediaTypeTVShow), any())
        Assert.assertNotNull(genreTVShow)
        assertEquals(dataResponseGenre.size, genreTVShow.size)
    }

    @Test
    fun getVideoTVShow() {
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadVideosCallback)
                .onAllVideosReceived(dataResponseVideo)
            null
        }.`when`(remote).getDetailVideos(
            eq(mediaTypeTVShow), eq(idTVShow), any()
        )

        val videoTVShow =
            LiveDataTestUtil.getValue(repository.getVideoDetail(mediaTypeTVShow, idTVShow))
        verify(remote).getDetailVideos(eq(mediaTypeTVShow), eq(idTVShow), any())
        Assert.assertNotNull(videoTVShow)
        assertEquals(dataResponseVideo.size, videoTVShow.size)
    }

    @Test
    fun getVideoMovie() {
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadVideosCallback)
                .onAllVideosReceived(dataResponseVideo)
            null
        }.`when`(remote).getDetailVideos(
            eq(mediaTypeMovie), eq(idMovie), any()
        )

        val videoMovie =
            LiveDataTestUtil.getValue(repository.getVideoDetail(mediaTypeMovie, idMovie))
        verify(remote).getDetailVideos(eq(mediaTypeMovie), eq(idMovie), any())
        Assert.assertNotNull(videoMovie)
        assertEquals(dataResponseVideo.size, videoMovie.size)
    }
}