package id.daydream.jetmovie.ui.watchlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import id.daydream.jetmovie.data.source.DataRepository
import id.daydream.jetmovie.data.source.local.entity.DataMovieTVEntity
import id.daydream.jetmovie.utils.DataDummy
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
class WatchListViewModelTest {

    private lateinit var viewModel: WatchListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<DataMovieTVEntity>>

    @Mock
    private lateinit var pagedList: PagedList<DataMovieTVEntity>

    @Before
    fun setup() {
        viewModel = WatchListViewModel(dataRepository)
    }

    @Test
    fun getWatchList() {
        val dummyMovieTV = pagedList
        `when`(dummyMovieTV.size).thenReturn(20)
        val movieTV = MutableLiveData<PagedList<DataMovieTVEntity>>()
        movieTV.value = dummyMovieTV


        `when`(dataRepository.getWatchList()).thenReturn(movieTV)
        val movieTVEntities = viewModel.getWatchList().value
        verify(dataRepository).getWatchList()
        assertNotNull(movieTVEntities)
        assertEquals(20, movieTVEntities?.size)

        viewModel.getWatchList().observeForever(observer)
        verify(observer).onChanged(dummyMovieTV)
    }

    @Test
    fun setWatchListMovie() {
        viewModel.addToWatchList(DataDummy.generateDetailDataMovie())
        verify(dataRepository).setWatchList(DataDummy.generateDetailDataMovie(), true)
        verifyNoMoreInteractions(dataRepository)
    }

    @Test
    fun setWatchListTVShow() {
        viewModel.addToWatchList(DataDummy.generateDetailDataTVShow())
        verify(dataRepository).setWatchList(DataDummy.generateDetailDataTVShow(), true)
        verifyNoMoreInteractions(dataRepository)
    }
}