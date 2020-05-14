package me.kolotilov.lifehackstudiowebapi.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import me.kolotilov.lifehackstudiowebapi.web.LifehackApi
import javax.inject.Inject

class OverviewViewModel(private val api: LifehackApi) : ViewModel() {

    val overviewData: Single<List<OverviewData>> get() {
        return api.getOverviewData()
            .map { it.map { it.toOverviewData() } }
            .subscribeOn(Schedulers.io())
    }

    class Factory @Inject constructor(
        private val api: LifehackApi
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return OverviewViewModel(api) as T
        }
    }
}