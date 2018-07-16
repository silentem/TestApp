package com.whaletail.uklon.test.dagger.modules

import com.whaletail.uklon.test.mvp.updateService.UpdateService
import com.whaletail.uklon.test.mvp.updateService.UpdateServiceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilder {

    @ContributesAndroidInjector(modules = arrayOf(UpdateServiceModule::class))
    abstract fun updateService(): UpdateService

}