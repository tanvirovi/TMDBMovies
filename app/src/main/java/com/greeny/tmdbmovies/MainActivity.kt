package com.greeny.tmdbmovies

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.greeny.tmdbmovies.databinding.ActivityMainBinding
import com.greeny.tmdbmovies.presentation.MovieAdapter
import com.greeny.tmdbmovies.presentation.MovieViewModel
import com.greeny.tmdbmovies.presentation.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    @Inject
    lateinit var movieAdapter: MovieAdapter
    lateinit var viewModel: MovieViewModel
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMovie.setupWithNavController(
            navController
        )

        viewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]
    }
}