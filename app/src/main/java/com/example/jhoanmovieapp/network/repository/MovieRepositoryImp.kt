package com.example.jhoanmovieapp.network.repository
import javax.inject.Inject
import com.example.jhoanmovie.model.APIResponse
import com.example.jhoanmovieapp.network.apiclient.ApiClient
import com.example.jhoanmovieapp.util.AppConstant
import retrofit2.Call


//class MovieRepositoryImp @Inject constructor(private val apiClient: ApiClient) : MovieRepository{
//
//
//    override fun getMostPopularMovie(): Call<APIResponse> {
//        return apiClient.getMostPopularMovie(AppConstant.API_KEY)
//    }
//
//    override fun getTopRatedMovie(): Call<APIResponse> {
//        return apiClient.getTopRatedMovie(AppConstant.API_KEY)
//    }
//
//    override fun getUpcomingMovie(): Call<APIResponse> {
//        return apiClient.getUpcomingMovie(AppConstant.API_KEY)
//    }
//
//}