package com.example.esc.movies;

import android.graphics.Bitmap;

import Beans.Movie;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 9/16/2016.
 */
public interface OnMovieSelected {

    public void selectedMovie(Movie movie , Bitmap bitmap);
}
