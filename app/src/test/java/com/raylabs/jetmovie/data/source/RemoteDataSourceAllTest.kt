package com.raylabs.jetmovie.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.raylabs.jetmovie.data.source.remote.RemoteDataSource
import com.raylabs.jetmovie.data.source.remote.StatusResponse
import com.raylabs.jetmovie.data.source.remote.response.DataGenre
import com.raylabs.jetmovie.data.source.remote.response.DataResponse
import com.raylabs.jetmovie.data.source.remote.response.GetDetailVideos
import com.raylabs.jetmovie.data.source.remote.response.ResultsGenre
import com.raylabs.jetmovie.data.source.remote.response.ResultsItem
import com.raylabs.jetmovie.data.source.remote.response.ResultsVideos
import com.raylabs.jetmovie.network.ApiService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceAllTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var callDataResponse: Call<DataResponse>

    @Mock
    lateinit var callResultsItem: Call<ResultsItem>

    @Mock
    lateinit var callVideos: Call<GetDetailVideos>

    @Mock
    lateinit var callGenre: Call<DataGenre>

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remoteDataSource = RemoteDataSource(apiService)
    }

    @Test
    fun `getAllUpcoming returns expected data`() {
        val dummyData = DataResponse(1, 1, listOf(makeDummyItem()), 1)
        `when`(apiService.getAllUpComingMovie()).thenReturn(callDataResponse)

        val result = remoteDataSource.getAllUpcoming()
        val captor =
            ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<DataResponse>>
        verify(callDataResponse).enqueue(captor.capture())
        captor.value.onResponse(callDataResponse, Response.success(dummyData))

        result.observeForever {
            assertEquals(StatusResponse.SUCCESS, it.status)
            assertEquals(dummyData.results, it.body)
        }
    }

    @Test
    fun `getMovieByKeyword returns expected data`() {
        val dummyData = DataResponse(1, 1, listOf(makeDummyItem()), 1)
        `when`(apiService.getMovieByKeyword(keyword = "action")).thenReturn(callDataResponse)

        val result = remoteDataSource.getMovieByKeyword("action")
        val captor =
            ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<DataResponse>>
        verify(callDataResponse).enqueue(captor.capture())
        captor.value.onResponse(callDataResponse, Response.success(dummyData))

        result.observeForever {
            assertEquals(StatusResponse.SUCCESS, it.status)
            assertEquals(dummyData.results, it.body)
        }
    }

    @Test
    fun `getAllTrending returns expected data`() {
        val dummyItem = makeDummyItem()
        val dummyResponse = DataResponse(1, 1, listOf(dummyItem), 1)

        `when`(apiService.getTrending(page = "1")).thenReturn(callDataResponse)

        val captor = ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<DataResponse>>
        val result = remoteDataSource.getAllTrending(page = "1")

        verify(callDataResponse).enqueue(captor.capture())
        captor.value.onResponse(callDataResponse, Response.success(dummyResponse))

        result.observeForever {
            assertEquals(StatusResponse.SUCCESS, it.status)
            assertEquals(listOf(dummyItem), it.body)
        }
    }

    @Test
    fun `getDetailMovie returns expected data`() {
        val dummyItem = makeDummyItem()
        `when`(apiService.getDetailMovie("123")).thenReturn(callResultsItem)

        val result = remoteDataSource.getDetailMovie("123")
        val captor =
            ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<ResultsItem>>
        verify(callResultsItem).enqueue(captor.capture())
        captor.value.onResponse(callResultsItem, Response.success(dummyItem))

        result.observeForever {
            assertEquals(StatusResponse.SUCCESS, it.status)
            assertEquals(dummyItem, it.body)
        }
    }

    @Test
    fun `getDetailTV returns expected data`() {
        val dummyItem = makeDummyItem()
        `when`(apiService.getDetailTV("tv123")).thenReturn(callResultsItem)

        val result = remoteDataSource.getDetailTV("tv123")
        val captor =
            ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<ResultsItem>>
        verify(callResultsItem).enqueue(captor.capture())
        captor.value.onResponse(callResultsItem, Response.success(dummyItem))

        result.observeForever {
            assertEquals(StatusResponse.SUCCESS, it.status)
            assertEquals(dummyItem, it.body)
        }
    }

    @Test
    fun `getDetailVideos returns expected data`() {
        val dummyVideo = ResultsVideos("YouTube", "Trailer", "Trailer", "abc123")
        val dummyResult = GetDetailVideos(1, listOf(dummyVideo))
        `when`(apiService.getDetailVideo("movie", "123")).thenReturn(callVideos)

        val result = remoteDataSource.getDetailVideos("movie", "123")
        val captor =
            ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<GetDetailVideos>>
        verify(callVideos).enqueue(captor.capture())
        captor.value.onResponse(callVideos, Response.success(dummyResult))

        result.observeForever {
            assertEquals(StatusResponse.SUCCESS, it.status)
            assertEquals(dummyResult.results, it.body)
        }
    }

    @Test
    fun `getAllGenre returns expected data`() {
        val dummyGenres = listOf(ResultsGenre(1, "Action"))
        val dummyResponse = DataGenre(dummyGenres)
        `when`(apiService.getGenre("movie")).thenReturn(callGenre)

        val result = remoteDataSource.getAllGenre("movie")
        val captor =
            ArgumentCaptor.forClass(Callback::class.java) as ArgumentCaptor<Callback<DataGenre>>
        verify(callGenre).enqueue(captor.capture())
        captor.value.onResponse(callGenre, Response.success(dummyResponse))

        result.observeForever {
            assertEquals(StatusResponse.SUCCESS, it.status)
            assertEquals(dummyResponse.genres, it.body)
        }
    }

    private fun makeDummyItem(): ResultsItem {
        return ResultsItem(
            id = "1",
            posterPath = "/poster.jpg",
            title = "Dummy Title",
            voteAverage = 9.0,
            genreIds = listOf(1, 2),
            name = null,
            mediaType = "movie",
            backdropPath = "/backdrop.jpg",
            overview = "A test movie",
            genres = null,
            releaseDate = "2025-01-01",
            firstAirDate = null
        )
    }
}
