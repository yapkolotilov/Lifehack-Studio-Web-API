package me.kolotilov.lifehackstudiowebapi.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import me.kolotilov.lifehackstudiowebapi.web.WebService
import javax.inject.Inject

class DetailsViewModel(
    private val web: WebService
) : ViewModel() {

    fun getDetails(id: Long): Single<DetailsData> {
        return web.getDetailsData(id)
            .subscribeOn(Schedulers.io())
    }

    class Factory @Inject constructor(
        private val web: WebService
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailsViewModel(web) as T
        }
    }
}