package com.example.jhoanmovieapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jhoanmovie.model.APIResponse
import com.example.jhoanmovie.model.Movie
import com.example.jhoanmovieapp.network.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class FavoritViewModel @Inject constructor(val repository: MovieRepository) : ViewModel() {


    var favoritMovie = MutableLiveData<List<Movie>>()
    var errorMessage = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()



    fun getFavoritMovie () {
        isLoading.postValue(true)
        repository.getUpcomingMovie().enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                isLoading.postValue(false)
                favoritMovie.postValue(response.body()!!.results!!)
            }
            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                isLoading.postValue(false)
                errorMessage.postValue(t.message)
            }
        })


    }







}