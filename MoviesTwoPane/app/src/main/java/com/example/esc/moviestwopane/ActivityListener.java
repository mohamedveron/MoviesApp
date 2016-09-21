package com.example.esc.moviestwopane;

import java.util.List;

import Beans.Movie;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed  on 8/6/2016.
 */
public class ActivityListener implements OnDataSendToActivity {

    @Override
    public void updateGridView(MovieAdapter adapter, List<Movie> details) {
        adapter.clear();
        for (Movie movie : details) {
            adapter.add(movie);
           // adapter.notifyDataSetChanged();
        }
    }
}
