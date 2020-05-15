package me.kolotilov.lifehackstudiowebapi.di

import dagger.Binds
import dagger.Module
import me.kolotilov.lifehackstudiowebapi.web.WebService
import me.kolotilov.lifehackstudiowebapi.web.WebServiceImpl
import javax.inject.Singleton

@Module
abstract class AbstractDataSourcesModule {

    @Binds
    @Singleton
    abstract fun webService(webServiceImpl: WebServiceImpl): WebService
}