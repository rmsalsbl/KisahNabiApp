package com.slowmotion.kisahnabiapp.repo

import com.slowmotion.kisahnabiapp.model.ResponseMain
import com.slowmotion.kisahnabiapp.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.logging.Handler

class RepositoryMain {

    fun getData(responhandler: (List<ResponseMain>) -> Unit, errerHandler: (Throwable) -> Unit){
        ConfigNetwork.service().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responhandler(it)
            }, {
                errerHandler(it)
            })
    }
}