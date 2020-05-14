package me.kolotilov.lifehackstudiowebapi.di

import dagger.Module
import dagger.Provides
import me.kolotilov.lifehackstudiowebapi.utils.BASE_URL
import me.kolotilov.lifehackstudiowebapi.web.LifehackApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataSourcesModule {

    @Provides
    @Singleton
    fun retrofit(): Retrofit = buildRetrofit()

    @Provides
    @Singleton
    fun api(retrofit: Retrofit): LifehackApi = retrofit.create(LifehackApi::class.java)


    private fun buildRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}