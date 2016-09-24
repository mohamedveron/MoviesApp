package com.example.esc.movies;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Beans.Movie;
import DBLayer.MovieModel;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle extras = getIntent().getExtras();
        if(savedInstanceState==null){
            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(extras);
            getFragmentManager().beginTransaction().add(R.id.details_container, detailsFragment).commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
}
