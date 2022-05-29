package com.mkl.search_data.di

import com.mk.seach_domain.repository.SearchRepository
import com.mkl.search_data.remote.DeezerApi
import com.mkl.search_data.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDataModule {
    @Provides
    @Singleton
    @Named("Search")
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): DeezerApi {
        return Retrofit.Builder().baseUrl(DeezerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create()
    }

    @Provides
    @Singleton
    fun provideRepository(api: DeezerApi): SearchRepository {
        return SearchRepositoryImpl(api)
    }
}