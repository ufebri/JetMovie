package com.bedboy.jetmovie.ui.home

import com.bedboy.jetmovie.model.entity.DataResult
import com.bedboy.jetmovie.model.entity.Results
import com.bedboy.jetmovie.model.repository.RemoteRepoImplement
import com.bedboy.jetmovie.network.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

class HomePresenter(
    val mView: HomeContract.View,
    private val mRepoImplement: RemoteRepoImplement,
    private val schedulerProvider: SchedulerProvider
) : HomeContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getResultItem(query: String?, pages: Int?) {
        mView.showLoading()
        compositeDisposable.add(
            mRepoImplement.getResultsItem(query, pages)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object : ResourceSubscriber<DataResult>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: DataResult?) {
                        mView.showResultItem(t?.searches as ArrayList<Results>)
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                    }
                })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}