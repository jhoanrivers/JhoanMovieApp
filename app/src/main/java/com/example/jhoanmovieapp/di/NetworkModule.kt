package com.example.jhoanmovieapp.di

import com.example.jhoanmovieapp.network.apiclient.ApiClient
import com.example.jhoanmovieapp.util.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttp() : OkHttpClient {
        val logging  = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }



    // provide API client for movie
    @Provides
    fun provideApiClient(okHttpClient : OkHttpClient) : ApiClient {

        val retrofit =  Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiClient::class.java)
    }


    // another API client







}