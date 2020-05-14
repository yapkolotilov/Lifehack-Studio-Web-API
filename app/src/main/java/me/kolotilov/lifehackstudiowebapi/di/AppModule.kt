package me.kolotilov.lifehackstudiowebapi.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.kolotilov.lifehackstudiowebapi.MainActivity
import me.kolotilov.lifehackstudiowebapi.overview.OverviewFragment

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun overviewFragment(): OverviewFragment
}