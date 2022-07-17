package com.example.jhoanmovieapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jhoanmovie.model.APIResponse
import com.example.jhoanmovie.model.Movie
import com.example.jhoanmovieapp.network.repository.MovieRepositoryImp2
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class TopRatedViewModel @Inject constructor(private val movieRepositoryImp2: MovieRepositoryImp2): ViewModel() {

    var listMovie = MutableLiveData<List<Movie>>()
    var errorMessage = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()


    fun getTopRatedMovie() {
        isLoading.postValue(true)
        movieRepositoryImp2.getTopRatedListMovie().enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                isLoading.postValue(false)
                listMovie.postValue(response.body()?.results!!)
            }
            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                isLoading.postValue(false)
                errorMessage.postValue(t.message)
            }
        })
    }
}
