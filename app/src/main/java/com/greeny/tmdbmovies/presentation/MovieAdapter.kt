package com.greeny.tmdbmovies.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greeny.tmdbmovies.data.model.Movie
import com.greeny.tmdbmovies.databinding.MovieListItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private val callback = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MovieViewHolder(val binding: MovieListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie : Movie){
            binding.descriptionTextView.text = movie.overview
            binding.titleTextView.text = movie.title
            binding.ratingTextView.text = "Rating : "  + movie.voteAverage.toString()
            val posterURL = "https://image.tmdb.org/t/p/w500"+movie.posterPath
            Glide.with(binding.imageView.context)
                .load(posterURL)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(movie)
                }
            }
        }
    }

    private var onItemClickListener :((Movie)->Unit)?=null
    fun setOnItemClickListener(listener : (Movie)->Unit){
        onItemClickListener = listener
    }
}