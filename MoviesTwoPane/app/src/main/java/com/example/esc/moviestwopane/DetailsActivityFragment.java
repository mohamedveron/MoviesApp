package com.example.esc.moviestwopane;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Beans.Movie;
import Beans.Trailer;
import Controller.DetailsController;
import Controller.Globals;
import DBLayer.MovieModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    public DetailsAdapter adapter;
    public ArrayList<Object>details;

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        View detailsHeader =  inflater.inflate(R.layout.details, container, false);

        ImageView poster = (ImageView)detailsHeader.findViewById(R.id.poster);
        TextView overview = (TextView)detailsHeader.findViewById(R.id.overView);
        TextView vote = (TextView)detailsHeader.findViewById(R.id.rate);
        TextView year = (TextView)detailsHeader.findViewById(R.id.year);

        ListView list = (ListView)view.findViewById(R.id.detailsList);
        list.addHeaderView(detailsHeader);

        final Intent intent = getActivity().getIntent();
        poster.setImageBitmap((Bitmap) intent.getParcelableExtra("poster"));
                overview.setText(intent.getStringExtra("overview"));
                final Double vote1 = intent.getDoubleExtra("vote", 80.1);
                vote.setText(vote1.toString());
                getActivity().setTitle(intent.getStringExtra("title"));
                year.setText(intent.getStringExtra("date"));

                Trailer t = new Trailer("trailer1","1","//shsldlsd");
                details = new ArrayList<>();
                adapter = new DetailsAdapter(getActivity(),R.layout.trailer,details);
                 DetailsController detailsController = new DetailsController();
                 detailsController.getTrials(adapter,intent.getStringExtra("id"));
        list.setAdapter(adapter);
                Button add = (Button)view.findViewById(R.id.add);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                MovieModel movieModel = new MovieModel(v.getContext());
                SQLiteDatabase sqLiteDatabase = movieModel.getWritableDatabase();
                movieModel.addMovie(new Movie(intent.getStringExtra("overview"),intent.getStringExtra("title"),
                        intent.getStringExtra("id")
                        ,intent.getStringExtra("posterPath"),vote1,intent.getStringExtra("date")));

            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = parent.getAdapter().getItem(position);
                if (object.getClass() == Trailer.class) {
                    Trailer trailer = (Trailer) object;
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey())));

                }
            }
        });
        return view;
    }
}
