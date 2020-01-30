package com.bedboy.jetmovie.ui.detail

import com.bedboy.jetmovie.model.entity.DataDetail
import com.bedboy.jetmovie.model.repository.RemoteRepoImplement
import com.bedboy.jetmovie.network.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber

class DetailPresenter(
    val mView: DetailContract.View,
    private val remoteRepoImplement: RemoteRepoImplement,
    private val scheduler: SchedulerProvider
) : DetailContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getResultItem(idIMDB: String?) {
        mView.showLoading()
        compositeDisposable.add(
            remoteRepoImplement.getDetailItem(idIMDB)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<DataDetail>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: DataDetail) {
                        mView.setItemProperty(t)
                    }

                    override fun onError(t: Throwable?) {
                        mView.hideLoading()
                        mView.showMessage(t?.message)
                    }
                })
        )
    }

    override fun onDestroyPresenter() {
        compositeDisposable.dispose()
    }
}