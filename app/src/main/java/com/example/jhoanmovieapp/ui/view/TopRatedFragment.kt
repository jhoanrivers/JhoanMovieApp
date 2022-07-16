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
import com.example.jhoanmovieapp.databinding.TopRatedFragmentBinding
import com.example.jhoanmovieapp.ui.adapter.MovieAdapter
import com.example.jhoanmovieapp.ui.viewmodel.TopRatedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedFragment : Fragment() {


    lateinit var binding: TopRatedFragmentBinding
    private lateinit var viewModel: TopRatedViewModel
    var movieAdapter = MovieAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TopRatedFragmentBinding.inflate(inflater, container, false)
        var root = binding.root

        initAdapter()
        initViewModel()
        observeViewModel()
        return root
    }

    /**
     * init adapter
     */
    private fun initAdapter() {
        binding.mostTopRatedRv.layoutManager = GridLayoutManager(this.context, 2)
        binding.mostTopRatedRv.adapter = movieAdapter
    }

    /**
     * init view model
     */
    private fun initViewModel() {
        viewModel.getTopRatedMovie()
    }


    /**
     * observe view model
     */
    private fun observeViewModel() {
        viewModel.listMovie.observe(viewLifecycleOwner) {
            movieAdapter.setMovie(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressTr.visibility = View.VISIBLE
            } else {
                binding.progressTr.visibility = View.GONE
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(this.context, "Failed To Load Movies", Toast.LENGTH_SHORT)
            Log.d("Top Rated", it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
    }

}