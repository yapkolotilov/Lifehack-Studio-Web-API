package me.kolotilov.lifehackstudiowebapi.web

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LifehackApi {

    @GET("test.php")
    fun getOverviewData(): Single<List<OverviewDto>>

    @GET("test.php")
    fun getDetailsData(@Query("id") id: Long): Single<List<DetailsDto>>
}