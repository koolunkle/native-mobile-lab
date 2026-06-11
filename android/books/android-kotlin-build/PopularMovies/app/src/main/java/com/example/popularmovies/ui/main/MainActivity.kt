package com.example.popularmovies.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.popularmovies.databinding.ActivityMainBinding
import com.example.popularmovies.domain.model.Movie
import com.example.popularmovies.ui.details.DetailsActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val movieAdapter by lazy { MovieAdapter { movie -> openMovieDetails(movie) } }

    // private val movieViewModel: MovieViewModel by viewModels { MovieViewModel.Factory }
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.movieList.adapter = movieAdapter
    }

    private fun observeViewModel() {
        /*
        movieViewModel.popularMovies.observe(this) { popularMovies ->
            movieAdapter.submitList(popularMovies)
        }

        movieViewModel.error.observe(this) { error ->
            if (error.isNotEmpty()) {
                Snackbar.make(binding.movieList, error, Snackbar.LENGTH_LONG).show()
            }
        }
        */

        /*
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    movieViewModel.popularMovies.collect { movies ->
                        movieAdapter.submitList(movies)
                    }
                }
                launch {
                    movieViewModel.error.collect { error ->
                        if (error.isNotEmpty()) {
                            Snackbar.make(binding.movieList, error, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        */

        /*
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is MovieUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is MovieUiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(uiState.movies)
                        }

                        is MovieUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Snackbar.make(binding.root, uiState.message, Snackbar.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
        */

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.popularMovies.collectLatest { pagingData ->
                    movieAdapter.submitData(pagingData)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieAdapter.loadStateFlow.collect { loadStates ->
                    val refreshState = loadStates.refresh

                    binding.progressBar.visibility = if (refreshState is LoadState.Loading) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }

                    if (refreshState is LoadState.Error) {
                        Snackbar.make(
                            binding.root,
                            refreshState.error.localizedMessage ?: "Error",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun openMovieDetails(movie: Movie) {
        /*
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_TITLE, movie.title)
            putExtra(DetailsActivity.EXTRA_RELEASE, movie.releaseDate)
            putExtra(DetailsActivity.EXTRA_OVERVIEW, movie.overview)
            putExtra(DetailsActivity.EXTRA_POSTER, movie.posterPath)
        }
        */

        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_MOVIE, movie)
        }
        startActivity(intent)
    }
}
