package me.kolotilov.lifehackstudiowebapi.di

import dagger.Binds
import dagger.Module
import me.kolotilov.lifehackstudiowebapi.web.WebService
import me.kolotilov.lifehackstudiowebapi.web.WebServiceImpl

@Module
abstract class AbstractDataSourcesModule {

    @Binds
    abstract fun webService(service: WebServiceImpl): WebService
}