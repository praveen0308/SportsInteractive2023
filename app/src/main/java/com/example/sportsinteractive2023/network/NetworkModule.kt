package com.example.sportsinteractive2023.network

import android.content.Context
import com.example.sportsinteractive2023.utils.ConnectivityInterceptor
import com.example.sportsinteractive2023.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(
        @ApplicationContext context: Context,
        connectivityInterceptor: ConnectivityInterceptor
    ): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        httpClient.addInterceptor(connectivityInterceptor)
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val factory = GsonConverterFactory.create(gson)
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(factory)
            .client(httpClient.build())
            .build()

    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}