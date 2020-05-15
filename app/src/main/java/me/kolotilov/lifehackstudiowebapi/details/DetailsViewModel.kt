package me.kolotilov.lifehackstudiowebapi.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import me.kolotilov.lifehackstudiowebapi.web.LifehackApi
import javax.inject.Inject

class DetailsViewModel(
    private val api: LifehackApi
) : ViewModel() {

    fun getDetails(id: Long): Single<DetailsData> {
        return api.getDetailsData(id)
            .map { it[0] }
            .map { it.toDetailsData() }
            .subscribeOn(Schedulers.io())
    }

    class Factory @Inject constructor(
        private val api: LifehackApi
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailsViewModel(api) as T
        }
    }
}