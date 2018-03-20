package com.whaletail.uklon.test.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * @author whaletail on 20.03.18.
 */
@Module(subcomponents = arrayOf())
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

}