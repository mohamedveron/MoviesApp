package com.example.esc.moviestwopane;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Beans.Movie;
import Controller.MovieController;
import DBLayer.MovieModel;
import Holders.MovieHolder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private List<Movie> movies;
    public MovieAdapter posters;
    public OnDataSendToActivity data;

    public MainActivityFragment() {
        data = new ActivityListener();
        movies = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        MovieModel movieModel = new MovieModel(getContext());
        SQLiteDatabase sqLiteDatabase = movieModel.getWritableDatabase();
        movies = movieModel.getAllMovies();

        GridView grid = (GridView)view.findViewById(R.id.gridView);
        posters = new MovieAdapter(getActivity(),R.layout.movie,movies);
        MovieController.getMostPopular(data, posters);
        grid.setAdapter(posters);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "position" + position, Toast.LENGTH_SHORT).show();
                MovieHolder holder = (MovieHolder) view.getTag();
                Movie movie = (Movie) holder.getPoster().getTag();
                holder.getPoster().buildDrawingCache();
                Bitmap bitmap = holder.getPoster().getDrawingCache();
                Intent intent = new Intent(parent.getContext(), DetailsActivity.class);
                intent.putExtra("title", movie.getTitle());
                intent.putExtra("overview", movie.getOverview());
                intent.putExtra("vote", movie.getVote());
                intent.putExtra("date", movie.getRelease_date());
                intent.putExtra("posterPath", movie.getPoster());
                intent.putExtra("id", movie.getId());
                intent.putExtra("poster", bitmap);
                startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_sortPopular){
            MovieController.getMostPopular(data, posters);
        }
        if(id == R.id.action_sortTopRated){
            MovieController.getTopRated(data, posters);
        }
        if(id == R.id.action_sortFavourite){
            MovieModel movieModel = new MovieModel(getContext());
            SQLiteDatabase sqLiteDatabase = movieModel.getWritableDatabase();
            movies = movieModel.getAllMovies();
            posters.clear();
            for (Movie movie : movies) {
                posters.add(movie);
            }
        }

        return true;
    }
}
