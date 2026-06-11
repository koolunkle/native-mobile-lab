package com.example.popularmovies.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import com.example.popularmovies.databinding.ViewMovieItemBinding
import com.example.popularmovies.domain.model.Movie

class MovieAdapter(private val onMovieClick: (Movie) -> Unit) :
// ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ViewMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(it, onMovieClick)
        }
    }

    class MovieViewHolder(private val binding: ViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, onMovieClick: (Movie) -> Unit) {
            binding.movieTitle.text = movie.title

            Glide.with(binding.root.context)
                .load(movie.posterUrl)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(binding.moviePoster)

            binding.root.setOnClickListener {
                onMovieClick(movie)
            }
        }
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}