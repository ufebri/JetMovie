package com.bedboy.jetmovie

import com.bedboy.jetmovie.data.entity.DataResult
import com.bedboy.jetmovie.data.entity.Results
import com.bedboy.jetmovie.data.repository.RemoteRepoImplement
import com.bedboy.jetmovie.network.SchedulerProvider
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class HomePresenterTest {

    @Mock
    lateinit var mView: HomeContract.View

    @Mock
    lateinit var repoImplement: RemoteRepoImplement

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: HomePresenter

    lateinit var dataResult: DataResult

    lateinit var mDataResult: Flowable<DataResult>

    private val results = mutableListOf<Results>()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        scheduler = TestSchedulerProvider()
        dataResult = DataResult(results, results.size, true)
        mDataResult = Flowable.just(dataResult)
        mPresenter = HomePresenter(mView, repoImplement, scheduler)
        Mockito.`when`(repoImplement.getResultsItem("Iron Man", "1"))
            .thenReturn(mDataResult)
    }

    @Test
    fun getDataMovies() {
        mPresenter.getResultItem("Iron Man")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).hideLoading()
    }

}