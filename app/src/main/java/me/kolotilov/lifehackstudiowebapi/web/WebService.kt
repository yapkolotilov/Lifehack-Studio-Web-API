package me.kolotilov.lifehackstudiowebapi.web

import io.reactivex.Single
import me.kolotilov.lifehackstudiowebapi.details.DetailsData
import me.kolotilov.lifehackstudiowebapi.overview.OverviewData

interface WebService {

    fun getOverviewData(): Single<List<OverviewData>>

    fun getDetailsData(id: Long): Single<DetailsData>
}