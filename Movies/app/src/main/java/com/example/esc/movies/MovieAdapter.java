package com.example.esc.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import Beans.Movie;
import Controller.Globals;
import Holders.MovieHolder;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed  on 8/2/2016.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    public List<Movie> objects;
    private Context context;
    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Movie getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        MovieHolder holder = null;
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.movie,parent,false);
            holder = new MovieHolder(view);
            view.setTag(holder);
        }else{
            holder = (MovieHolder)view.getTag();
        }
        Movie movie =  objects.get(position);
        Picasso.with(context).load(Globals.URL+movie.getPoster())
                .into(holder.poster);
        holder.getPoster().setTag(movie);
        return view;
    }
}
