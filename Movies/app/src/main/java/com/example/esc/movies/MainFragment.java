package com.example.esc.movies;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
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

public class MainFragment extends Fragment {

    private List<Movie> movies;
    public MovieAdapter posters;
    public OnMovieSelected listner;
    public OnDataSendToActivity data;

    public MainFragment() {
        // Required empty public constructor
        data = new ActivityListener();
        movies = new ArrayList<>();
    }

    public void setListner(OnMovieSelected listner1) {
        listner = listner1;
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
        movies.add(new Movie("","","","",2.0,""));

        GridView grid = (GridView)view.findViewById(R.id.gridView);
        posters = new MovieAdapter(getActivity(),R.layout.movie,movies);
        MovieController.getMostPopular(data, posters);
        grid.setAdapter(posters);

        MovieController.getMostPopular(data, posters);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieHolder holder = (MovieHolder) view.getTag();
                Movie movie = (Movie) holder.getPoster().getTag();
                holder.getPoster().buildDrawingCache();
                Bitmap bitmap = holder.getPoster().getDrawingCache();
                listner.selectedMovie(movie,bitmap);

            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main_screen, menu);

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
            MovieModel movieModel = new MovieModel(getActivity());
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
