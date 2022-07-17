package com.example.jhoanmovieapp.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jhoanmovieapp.databinding.FavoritFragmentBinding
import com.example.jhoanmovieapp.ui.adapter.MovieAdapter
import com.example.jhoanmovieapp.ui.viewmodel.FavoritViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritFragment : Fragment() {


    private lateinit var viewModel: FavoritViewModel
    val adapter = MovieAdapter()
    lateinit var binding: FavoritFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FavoritFragmentBinding.inflate(layoutInflater,container, false)
        var view = binding.root

        initAdapter()
        initViewModel()
        observeViewModel()

        return view
    }

    /**
     * observe view model
     */
    private fun observeViewModel() {
        viewModel.favoritMovie.observe(viewLifecycleOwner){
            adapter.setMovie(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner)  {
            Log.e("FavoritFragment", it)
            Toast.makeText(this.context, "Failed Get Favorit", Toast.LENGTH_SHORT)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if(it){
                binding.progressMp.visibility = View.VISIBLE
            } else {
                binding.progressMp.visibility = View.GONE
            }
        }
    }

    /**
     * init view model
     */
    private fun initViewModel() {
        viewModel.getFavoritMovie()
    }


    /**
     * init adapter
     */
    private fun initAdapter() {
        binding.favoritRv.layoutManager = GridLayoutManager(this.context, 2)
        binding.favoritRv.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritViewModel::class.java)
    }

}