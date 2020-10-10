package com.raditya.wikipedia.common.di.module

import com.raditya.wikipedia.common.service.WikipediaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    internal fun providesOkHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    internal fun providesRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .baseUrl("http://192.168.1.5:45457/")
            .build()
    }

    @Provides
    @Singleton
    internal fun providesService(retrofit: Retrofit): WikipediaService {
        return retrofit.create(WikipediaService::class.java)
    }
}
