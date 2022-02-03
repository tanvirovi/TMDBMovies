package com.greeny.tmdbmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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

        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val movie = movieAdapter.differ.currentList[position]
                viewModel.deleteMovie(movie)
                Snackbar.make(view,"deleted Successfully", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo"){
                            viewModel.saveMovies(movie)
                        }
                        show()
                    }
            }

        }

        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(fragmentSavedBinding.rcvSavedMovie)
        }
    }

    private fun initRecyclerView(){
        fragmentSavedBinding.rcvSavedMovie.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}