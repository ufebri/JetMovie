package id.daydream.jetmovie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import id.daydream.jetmovie.data.source.local.LocalDataSource
import id.daydream.jetmovie.data.source.local.entity.DataMovieTVEntity
import id.daydream.jetmovie.data.source.local.entity.GenreEntity
import id.daydream.jetmovie.data.source.local.entity.VideoEntity
import id.daydream.jetmovie.data.source.remote.RemoteDataSource
import id.daydream.jetmovie.util.LiveDataTestUtil
import id.daydream.jetmovie.util.PagedListUtil
import id.daydream.jetmovie.utils.AppExecutors
import id.daydream.jetmovie.utils.DataDummy
import id.daydream.jetmovie.vo.Resource
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class JetMovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val repository = FakeDataRepository(local, appExecutors, remote)

    private val dataResponseHome = DataDummy.generateRemoteData()
    private val dataResponseGenre = DataDummy.generateRemoteGenre()
    private val dataResponseVideo = DataDummy.generateRemoteVideo()
    private val dataResponseMovie = DataDummy.generateDetailDataMovie()
    private val dataResponseTV = DataDummy.generateDetailDataTVShow()

    private val mediaTypeMovie = dataResponseHome[0].mediaType
    private val mediaTypeTVShow = dataResponseHome[13].mediaType

    private val idMovie = dataResponseHome[0].id
    private val idTVShow = dataResponseHome[13].id


    @Test
    fun getTrending() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataMovieTVEntity>
        `when`(local.getTrending()).thenReturn(dataSourceFactory)
        repository.getTrending()

        val trendingEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateData()))
        verify(local).getTrending()
        Assert.assertNotNull(trendingEntities.data)
        assertEquals(dataResponseHome.size.toLong(), trendingEntities.data?.size?.toLong())
    }

    @Test
    fun getPopular() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataMovieTVEntity>
        `when`(local.getPopular()).thenReturn(dataSourceFactory)
        repository.getPopular()

        val trendingEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateData()))
        verify(local).getPopular()
        Assert.assertNotNull(trendingEntities.data)
        assertEquals(dataResponseHome.size.toLong(), trendingEntities.data?.size?.toLong())
    }

    @Test
    fun getGenreMovie() {
        val dummyGenreMovie = MutableLiveData<List<GenreEntity>>()
        dummyGenreMovie.value = DataDummy.generateGenre()
        `when`(local.getGenre()).thenReturn(dummyGenreMovie)

        val genreMovie = LiveDataTestUtil.getValue(repository.getGenre(mediaTypeMovie))
        verify(local).getGenre()
        Assert.assertNotNull(genreMovie)
        assertEquals(dataResponseGenre.size, genreMovie.data?.size)
    }

    @Test
    fun getGenreTVShow() {
        val dummyGenreTV = MutableLiveData<List<GenreEntity>>()
        dummyGenreTV.value = DataDummy.generateGenre()
        `when`(local.getGenre()).thenReturn(dummyGenreTV)

        val genreTV = LiveDataTestUtil.getValue(repository.getGenre(mediaTypeMovie))
        verify(local).getGenre()
        Assert.assertNotNull(genreTV)
        assertEquals(dataResponseGenre.size, genreTV.data?.size)
    }

    @Test
    fun getVideoTVShow() {
        val dummyVideoTVShow = MutableLiveData<List<VideoEntity>>()
        dummyVideoTVShow.value = DataDummy.generateVideo()
        `when`(local.getVideo(idTVShow)).thenReturn(dummyVideoTVShow)

        val videoTVShow =
            LiveDataTestUtil.getValue(repository.getVideoDetail(mediaTypeTVShow, idTVShow))
        verify(local).getVideo(idTVShow)
        Assert.assertNotNull(videoTVShow)
        assertEquals(dataResponseVideo.size, videoTVShow.data?.size)
    }

    @Test
    fun getVideoMovie() {
        val dummyVideoMovie = MutableLiveData<List<VideoEntity>>()
        dummyVideoMovie.value = DataDummy.generateVideo()
        `when`(local.getVideo(idMovie)).thenReturn(dummyVideoMovie)

        val videoMovie =
            LiveDataTestUtil.getValue(repository.getVideoDetail(mediaTypeMovie, idMovie))
        verify(local).getVideo(idMovie)
        Assert.assertNotNull(videoMovie)
        assertEquals(dataResponseVideo.size, videoMovie.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDataMovie = MutableLiveData<DataMovieTVEntity>()
        dummyDataMovie.value = DataDummy.generateDetailDataMovie()
        `when`(local.getDetail(idMovie)).thenReturn(dummyDataMovie)

        val detailMovie =
            LiveDataTestUtil.getValue(repository.getDetailMovie(idMovie))
        verify(local).getDetail(idMovie)
        Assert.assertNotNull(detailMovie)
        assertEquals(dataResponseMovie, detailMovie.data)
    }

    @Test
    fun getDetailTVShow() {
        val dummyDataTVShow = MutableLiveData<DataMovieTVEntity>()
        dummyDataTVShow.value = DataDummy.generateDetailDataTVShow()
        `when`(local.getDetail(idTVShow)).thenReturn(dummyDataTVShow)

        val detailTVShow =
            LiveDataTestUtil.getValue(repository.getDetailTV(idTVShow))
        verify(local).getDetail(idTVShow)
        Assert.assertNotNull(detailTVShow)
        assertEquals(dataResponseTV, detailTVShow.data)
    }

    @Test
    fun setWatchListMovie() {
        repository.setWatchList(DataDummy.generateDetailDataMovie(), true)
        verify(local).setWatchList(DataDummy.generateDetailDataMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun setWatchListTVShow() {
        repository.setWatchList(DataDummy.generateDetailDataTVShow(), true)
        verify(local).setWatchList(DataDummy.generateDetailDataTVShow(), true)
        verifyNoMoreInteractions(local)
    }

}