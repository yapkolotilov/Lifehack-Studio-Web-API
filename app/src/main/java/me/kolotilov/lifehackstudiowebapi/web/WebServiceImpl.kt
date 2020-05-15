package me.kolotilov.lifehackstudiowebapi.web

import io.reactivex.Single
import me.kolotilov.lifehackstudiowebapi.details.DetailsData
import me.kolotilov.lifehackstudiowebapi.details.toDetailsData
import me.kolotilov.lifehackstudiowebapi.overview.OverviewData
import me.kolotilov.lifehackstudiowebapi.overview.toOverviewData
import javax.inject.Inject

class WebServiceImpl @Inject constructor(
    val api: LifehackApi
) : WebService {
    override fun getOverviewData(): Single<List<OverviewData>> {
        return api.getOverviewData()
            .map { it.map { it.toOverviewData() } }
    }

    override fun getDetailsData(id: Long): Single<DetailsData> {
        return api.getDetailsData(id)
            .map { it[0] }
            .map { it.toDetailsData() }
    }
}