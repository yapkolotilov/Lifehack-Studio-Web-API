package me.kolotilov.lifehackstudiowebapi.overview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import me.kolotilov.lifehackstudiowebapi.web.WebService
import javax.inject.Inject

class OverviewViewModel(private val web: WebService) : ViewModel() {

    val overviewData: Single<List<OverviewData>> get() {
        return web.getOverviewData()
            .subscribeOn(Schedulers.io())
    }

    class Factory @Inject constructor(
        private val web: WebService
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return OverviewViewModel(web) as T
        }
    }
}