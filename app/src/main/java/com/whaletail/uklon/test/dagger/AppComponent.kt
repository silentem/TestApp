package com.whaletail.uklon.test.dagger

import android.app.Application
import com.whaletail.uklon.test.TestApp
import com.whaletail.uklon.test.dagger.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication


/**
 * @author whaletail on 20.03.18.
 */

@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ApiModule::class,
        ActivityBuilder::class,
        ServiceBuilder::class))
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)

    fun inject(app: TestApp)

}