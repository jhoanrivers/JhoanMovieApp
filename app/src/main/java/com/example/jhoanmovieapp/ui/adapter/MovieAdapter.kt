package com.example.jhoanmovieapp.ui.adapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jhoanmovie.model.Movie
import com.example.jhoanmovieapp.R
import com.example.jhoanmovieapp.databinding.ItemMovieBinding
import com.example.jhoanmovieapp.ui.view.DetailMovieActivity
import com.example.jhoanmovieapp.util.AppConstant

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MostViewHolder>() {

    var movieList = mutableListOf<Movie>()


    fun setMovie(list: List<Movie>) {
        movieList = list.toMutableList()
        Log.d("adapter", list.toString())
        notifyDataSetChanged()
    }

    class MostViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostViewHolder {
        var binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MostViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MostViewHolder, position: Int) {
        var movie = movieList[position]

        Log.d("MOVIE", movie.toString())

        holder.binding.titleTv.text = movie.title
        var imageUrl = "${AppConstant.IMAGE_URL}${movie.posterPath}?api_key=${AppConstant.API_KEY}"
        if (movie.posterPath != null) {
            Glide.with(holder.itemView.context).load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background).into(holder.binding.movieImg)
        }

        holder.itemView.setOnClickListener {
            var intent = Intent(it.context, DetailMovieActivity::class.java)
            var bundle = Bundle()
            bundle.putParcelable("movie", movie)
            intent.putExtra("bundle", bundle)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }


}