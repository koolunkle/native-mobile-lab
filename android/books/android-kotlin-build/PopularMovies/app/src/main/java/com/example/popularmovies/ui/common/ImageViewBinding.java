package com.example.popularmovies.ui.common;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;

public class ImageViewBinding {
    @BindingAdapter("imageUrl")
    public static void bindImageUrl(ImageView view, String url) {
        if (url != null && !url.isEmpty()) {
            Glide.with(view.getContext())
                .load("https://image.tmdb.org/t/p/w185/" + url)
                .into(view);
        }
    }
}
