package com.greeny.tmdbmovies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greeny.tmdbmovies.databinding.FragmentMovieBinding
import com.greeny.tmdbmovies.presentation.MovieAdapter
import com.greeny.tmdbmovies.presentation.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MovieFragment : Fragment() {
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMovieBinding = FragmentMovieBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        movieAdapter = (activity as MainActivity).movieAdapter
        movieAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_movie",it)
            }
            findNavController().navigate(
                R.id.action_movieFragment_to_movieInfoFragment,
                bundle
            )
        }
        initRCV()
        viewMovieList()
        setSearchView()
    }

    private fun viewMovieList(){
        viewModel.getMovies(page)
        viewModel.popularMovies.observe(viewLifecycleOwner){ response ->
            when (response) {
                is com.greeny.tmdbmovies.data.util.Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        Log.e("serachedsssssssssssssss",it.movies.toString())

                        movieAdapter.differ.submitList(it.movies.toList())
                        pages = if(it.totalResults%20 == 0) {
                            it.totalResults / 20
                        }else{
                            it.totalResults/20+1
                        }
                        isLastPage = page == pages
                    }
                }
                is  com.greeny.tmdbmovies.data.util.Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error Occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is  com.greeny.tmdbmovies.data.util.Resource.Loading -> {
                    showProgressBar()
                }
            }

        }
    }

    private fun  initRCV(){
//        movieAdapter = MovieAdapter()
        fragmentMovieBinding.rcvMovie.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@MovieFragment.onScrollListener)
        }
    }

    private fun showProgressBar(){
        isLoading = true
        fragmentMovieBinding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        isLoading = false
        fragmentMovieBinding.progressBar.visibility = View.INVISIBLE
    }
    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentMovieBinding.rcvMovie.layoutManager as LinearLayoutManager
            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItem = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()
            val hasReachedToEnd = topPosition+visibleItem >= sizeOfCurrentList
            val shouldPaginate = !isLoading && isScrolling && hasReachedToEnd && !isLastPage
            if (shouldPaginate){
                page++
                viewModel.getMovies(page)
                isScrolling = false
            }
        }
    }

    //Search
    private fun setSearchView(){
        fragmentMovieBinding.searchViewMovies.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.searchMovies(p0.toString())
                viewSearchedMovies()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                viewModel.searchMovies(p0.toString())
                viewSearchedMovies()

                return false
            }
        })
        fragmentMovieBinding.searchViewMovies.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                initRCV()
                viewMovieList()
                return false
            }

        })
    }
    fun viewSearchedMovies(){
        viewModel.searchedMovies.observe(viewLifecycleOwner){ response ->
            when (response) {
                is com.greeny.tmdbmovies.data.util.Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        movieAdapter.differ.submitList(it.movies.toList())
                        pages = if(it.totalResults%20 == 0) {
                            it.totalResults / 20
                        }else{
                            it.totalResults/20+1
                        }
                        isLastPage = page == pages
                    }
                }
                is  com.greeny.tmdbmovies.data.util.Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error Occurred : $it", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is  com.greeny.tmdbmovies.data.util.Resource.Loading -> {
                    showProgressBar()
                }
            }

        }

    }
}