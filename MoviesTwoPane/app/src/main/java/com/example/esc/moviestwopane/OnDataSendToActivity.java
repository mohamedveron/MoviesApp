package com.example.esc.moviestwopane;

import java.util.List;

import Beans.Movie;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed  on 8/4/2016.
 */
public interface OnDataSendToActivity {
    public void updateGridView(MovieAdapter adapter, List<Movie> details);
}
