package com.example.jhoanmovieapp.network.repository

import com.example.jhoanmovie.model.APIResponse
import com.example.jhoanmovieapp.network.apiclient.ApiClient
import retrofit2.Call

interface MovieRepository {

    fun getMostPopularMovie() : Call<APIResponse>

    fun getTopRatedMovie() : Call<APIResponse>

    fun getUpcomingMovie() : Call<APIResponse>

}