package com.example.jhoanmovieapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jhoanmovie.model.APIResponse
import com.example.jhoanmovie.model.Movie
import com.example.jhoanmovieapp.network.repository.MovieRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class PopularViewModel @Inject constructor(val repository: MovieRepository): ViewModel() {

    var movieList  = MutableLiveData<List<Movie>>()
    var errormessage = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()



    fun getListMovie() {
        isLoading.postValue(true)
        repository.getMostPopularMovie().enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                isLoading.postValue(false)
                movieList.postValue(response.body()!!.results!!)
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                isLoading.postValue(false)
                errormessage.postValue(t.message)
            }
        })
    }
}