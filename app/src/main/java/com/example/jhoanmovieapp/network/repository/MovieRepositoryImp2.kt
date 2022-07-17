package com.example.jhoanmovieapp.network.repository

import com.example.jhoanmovie.model.APIResponse
import com.example.jhoanmovieapp.network.apiclient.ApiClient
import com.example.jhoanmovieapp.util.AppConstant
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MovieRepositoryImp2 @Inject constructor(private val apiClient: ApiClient) {

    fun getPopularListMovie()  : Call<APIResponse> {
        return apiClient.getMostPopularMovie(AppConstant.API_KEY);
    }

    fun getTopRatedListMovie()  : Call<APIResponse> {
        return apiClient.getTopRatedMovie(AppConstant.API_KEY);
    }

    fun getUpcomingListMovie()  : Call<APIResponse> {
        return apiClient.getUpcomingMovie(AppConstant.API_KEY);
    }





}