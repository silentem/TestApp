package com.whaletail.uklon.test.dagger.modules

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author whaletail on 21.03.18.
 */
@Module
class NetworkModule(private val baseUrl: String) {

    internal val httpLoggingInterceptor: HttpLoggingInterceptor
        @Provides
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor

        }

    @Provides
    internal fun getHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
    }

    @Provides
    internal fun getRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(gsonConverterFactory)
                    .build()

    @Provides
    internal fun getGson(): GsonConverterFactory {
        return GsonConverterFactory
                .create(GsonBuilder()
                        .setLenient()
                        .create())
    }

}
