package com.example.jhoanmovieapp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.jhoanmovie.model.Movie
import com.example.jhoanmovieapp.R
import com.example.jhoanmovieapp.databinding.ActivityDetailMovieBinding
import com.example.jhoanmovieapp.util.AppConstant
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener


class DetailMovieActivity : AppCompatActivity() {

    var TAG = "DetailMovieActivity"

    lateinit var binding: ActivityDetailMovieBinding
    lateinit var movie: Movie


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAllData()
        onClickListener()

        setupToolbar()

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    /**
     * setup toolbar
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.detailToolbar)
        binding.detailToolbar.menu
        if(supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
            supportActionBar!!.setDisplayShowHomeEnabled(true);
            handleCollapsedToolbarTitle()

        }
    }


    /**
     * handle collapse
     */
    private fun handleCollapsedToolbarTitle() {
        var isShow = true
        var scrollRange = -1

        binding.appbarLayout.addOnOffsetChangedListener( AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if(scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if(scrollRange + verticalOffset == 0) {
                binding.collapsingToolbar.title = movie.title
                isShow = true
            } else if (isShow) {
                binding.collapsingToolbar.title = ""
                isShow = false
            }
        })
    }

    private fun onClickListener() {

    }

    private fun initAllData() {
        var bundle = intent.getBundleExtra("bundle")!!
        movie = bundle.getParcelable<Movie>("movie") as Movie
        Log.d(TAG, movie.toString())

        var backropUrl =
            "${AppConstant.IMAGE_URL}${movie.backdropPath}?api_key=${AppConstant.API_KEY}"
        var imageUrl =
            "${AppConstant.IMAGE_URL}${movie.posterPath}?api_key=${AppConstant.API_KEY}"
        Glide.with(this).load(backropUrl)
            .placeholder(android.R.color.holo_green_light)
            .error(android.R.color.holo_red_light)
            .fallback(android.R.color.holo_orange_light)
            .into(binding.bgImg)
        Glide.with(this).load(imageUrl)
            .placeholder(android.R.color.holo_green_light)
            .error(android.R.color.holo_red_light)
            .fallback(android.R.color.holo_orange_light)
            .into(binding.posterImg)
        binding.titleTv.text = movie.title
        binding.ratingTv.text = movie.voteAverage.toString()
        binding.peopleTv.text = movie.popularity.toString()
        binding.releaseDateTv.text = movie.releaseDate
        binding.durationTv.text = "-"
        binding.descTv.text = movie.overview
        binding.trailerTv.text = movie.overview
    }


}