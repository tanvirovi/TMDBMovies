package com.greeny.tmdbmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.greeny.tmdbmovies.databinding.FragmentSavedBinding
import com.greeny.tmdbmovies.presentation.MovieAdapter
import com.greeny.tmdbmovies.presentation.MovieViewModel

class SavedFragment : Fragment() {

    private lateinit var fragmentSavedBinding: FragmentSavedBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSavedBinding = FragmentSavedBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        movieAdapter = (activity as MainActivity).movieAdapter

        movieAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_movie",it)
            }
            findNavController().navigate(
                R.id.action_savedFragment_to_movieInfoFragment,
                bundle
            )
        }

        initRecyclerView()
        viewModel.getSavedMovies().observe(viewLifecycleOwner){
            movieAdapter.differ.submitList(it)
        }

    }

    private fun initRecyclerView(){
        fragmentSavedBinding.rcvSavedMovie.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}