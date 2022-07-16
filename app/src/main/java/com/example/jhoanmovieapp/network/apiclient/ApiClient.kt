package com.example.jhoanmovieapp.network.apiclient

import com.example.jhoanmovie.model.APIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("movie/popular")
    fun getMostPopularMovie(
        @Query("api_key") apikey : String
    ) : Call<APIResponse>


    @GET("movie/top_rated")
    fun getTopRatedMovie(
        @Query("api_key") apikey: String
    ) : Call<APIResponse>


    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") apiKey : String
    ) : Call<APIResponse>

}