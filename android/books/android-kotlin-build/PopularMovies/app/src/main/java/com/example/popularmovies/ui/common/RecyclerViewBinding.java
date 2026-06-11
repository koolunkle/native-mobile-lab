//package com.example.popularmovies.ui.common;
//
//import androidx.databinding.BindingAdapter;
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.popularmovies.domain.model.Movie;
//import com.example.popularmovies.ui.main.MovieAdapter;
//import java.util.List;
//
//public class RecyclerViewBinding {
//    @BindingAdapter("list")
//    public static void bindMovies(RecyclerView view, List<Movie> movies) {
//        MovieAdapter adapter = (MovieAdapter) view.getAdapter();
//        if (adapter != null) {
//            adapter.submitList(movies);
//        }
//    }
//}
