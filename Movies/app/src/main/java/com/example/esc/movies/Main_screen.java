package com.example.esc.movies;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import Beans.Movie;

public class Main_screen extends AppCompatActivity implements OnMovieSelected {
    public Boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Toast.makeText(this,"YOU MUST BE CONNECTED WITH INTERNET AND SELECT OPTIONS FROM ABOVE MENU TO SHOW MOVIES :)"
                ,Toast.LENGTH_LONG).show();

        if(savedInstanceState==null){
            MainFragment mainfragment = new MainFragment();
            mainfragment.setListner(this);
          getFragmentManager().beginTransaction().add(R.id.fPanel_One, mainfragment).commit();
        }
        FrameLayout frame2 = ( FrameLayout)findViewById(R.id.fPanel_Two);
        if(frame2 == null){
            twoPane = false;
        }
        else{
            twoPane = true;
        }

        MainFragment mainfragment = new MainFragment();
        mainfragment.setListner(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void selectedMovie(Movie movie , Bitmap bitmap) {
        if(twoPane){
            DetailsFragment detailsFragment = new DetailsFragment();
            Bundle extras = new Bundle();
            extras.putString("title", movie.getTitle());
            extras.putString("overview", movie.getOverview());
            extras.putDouble("vote", movie.getVote());
            extras.putString("date", movie.getRelease_date());
            extras.putString("posterPath", movie.getPoster());
            extras.putString("id", movie.getId());
            extras.putParcelable("poster", bitmap);
            detailsFragment.setArguments(extras);
            getFragmentManager().beginTransaction().replace(R.id.fPanel_Two, detailsFragment).commit();
        }
        else{
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("title", movie.getTitle());
            intent.putExtra("overview", movie.getOverview());
            intent.putExtra("vote", movie.getVote());
            intent.putExtra("date", movie.getRelease_date());
            intent.putExtra("posterPath", movie.getPoster());
            intent.putExtra("id", movie.getId());
            intent.putExtra("poster", bitmap);
            startActivity(intent);
        }
    }
}
