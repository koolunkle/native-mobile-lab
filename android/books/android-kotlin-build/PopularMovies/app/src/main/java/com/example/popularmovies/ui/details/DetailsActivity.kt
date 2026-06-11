package com.example.popularmovies.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.popularmovies.R
import com.example.popularmovies.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    companion object {
        /*const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
        const val EXTRA_TITLE = "title"
        const val EXTRA_RELEASE = "release"
        const val EXTRA_OVERVIEW = "overview"
        const val EXTRA_POSTER = "poster"*/
        const val EXTRA_MOVIE = "movie"
    }

    private lateinit var binding: ActivityDetailsBinding

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)*/

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*val movieData = Movie(
            title = intent.getStringExtra(EXTRA_TITLE).orEmpty(),
            releaseDate = intent.getStringExtra(EXTRA_RELEASE).orEmpty().take(4),
            overview = intent.getStringExtra(EXTRA_OVERVIEW).orEmpty(),
            posterPath = intent.getStringExtra(EXTRA_POSTER).orEmpty(),
        )*/

        // binding.movie = movieData
        binding.viewModel = detailsViewModel
        binding.lifecycleOwner = this

        /*val extras = intent.extras
        binding.titleText.text = extras?.getString(EXTRA_TITLE).orEmpty()
        binding.releaseText.text = extras?.getString(EXTRA_RELEASE).orEmpty().take(4)

        binding.overviewText.text =
            getString(R.string.movie_overview, extras?.getString(EXTRA_OVERVIEW).orEmpty())

        val posterPath = extras?.getString(EXTRA_POSTER).orEmpty()
        Glide.with(this@DetailsActivity)
            .load("$IMAGE_URL$posterPath")
            .placeholder(R.mipmap.ic_launcher)
            .fitCenter()
            .into(binding.moviePoster)*/
    }
}