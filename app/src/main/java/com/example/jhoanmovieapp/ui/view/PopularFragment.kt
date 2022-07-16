package com.example.jhoanmovieapp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jhoanmovieapp.databinding.FragmentPopularBinding
import com.example.jhoanmovieapp.ui.adapter.MovieAdapter
import com.example.jhoanmovieapp.ui.viewmodel.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PopularFragment : Fragment() {

    private lateinit var popularViewModel: PopularViewModel
    lateinit var binding: FragmentPopularBinding
    var mostPopularAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        val root = binding.root

        initAdapter()
        initViewModel()
        observeViewModel()
        return root
    }

    /**
     * observe view model
     */
    private fun observeViewModel() {

        popularViewModel.movieList.observe(viewLifecycleOwner) {
            mostPopularAdapter.setMovie(it)
            binding.mostPopularRv.adapter = mostPopularAdapter
        }
        popularViewModel.errormessage.observe(viewLifecycleOwner){
            Log.e("MOST_POPULAR", it)
        }
        popularViewModel.isLoading.observe(viewLifecycleOwner) {
            if(it){
                binding.progressMp.visibility = View.VISIBLE
            } else{
                binding.progressMp.visibility = View.GONE
            }
        }
    }

    /**
     * init view model
     */
    private fun initViewModel() {
        popularViewModel.getListMovie()
    }

    /**
     * init adapter
     */
    private fun initAdapter() {
        binding.mostPopularRv.layoutManager = GridLayoutManager(this.context, 2)

    }

}