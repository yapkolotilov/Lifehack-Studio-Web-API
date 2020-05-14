package me.kolotilov.lifehackstudiowebapi.web

import io.reactivex.Single
import retrofit2.http.GET

interface LifehackApi {

    @GET("test.php")
    fun getOverviewData(): Single<List<OverviewDto>>
}