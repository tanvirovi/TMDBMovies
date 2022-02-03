package com.greeny.tmdbmovies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.greeny.tmdbmovies.databinding.FragmentMovieInfoBinding
import com.greeny.tmdbmovies.presentation.MovieViewModel

class MovieInfoFragment : Fragment() {
    private lateinit var fragmentMovieInfoBinding: FragmentMovieInfoBinding
    private lateinit var viewModel : MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMovieInfoBinding = FragmentMovieInfoBinding.bind(view)
        val args : MovieInfoFragmentArgs by navArgs()
        val movie = args.selectedMovie

        viewModel = (activity as MainActivity).viewModel

        fragmentMovieInfoBinding.apply {
            movieName.text = movie.title
            movieOverview.text = movie.overview
            releaseDate.text = movie.releaseDate
        }
        val posterURL = "https://image.tmdb.org/t/p/w500"+movie.posterPath
        Glide.with(fragmentMovieInfoBinding.imageInfoView.context)
            .load(posterURL)
//            .override(600,300)
            .into(fragmentMovieInfoBinding.imageInfoView)

        fragmentMovieInfoBinding.fabSave.setOnClickListener {
            viewModel.saveMovies(movie)
            Snackbar.make(view,"movie saved",Snackbar.LENGTH_LONG).show()
        }
    }
}