package DBLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Beans.Movie;

/**
 * Created by ESC on 9/10/2016.
 */
public class MovieModel extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "movieDB";

    // Contacts table name
    private static final String movies = "favourite_movies";

    // Contacts Table Columns names
    private static final String title = "title";
    private static final String overview = "overview";
    private static final String id = "id";
    private static final String poster = "poster";
    private static final String vote = "vote";
    private static final String release_date = "release_date";
    private static final String createStm ="CREATE TABLE "+movies+" ( "+id+" INTEGER PRIMARY KEY, "
            +title+" VARCHAR(45), "+overview+" VARCHAR(1000), "+vote+" VARCHAR(10), "
            +release_date+" VARCHAR(10), "+poster+" VARCHAR(50) )";

    public MovieModel(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createStm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + movies);

        // Create tables again
        onCreate(db);
    }

    // Adding new movie
    public void addMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(id, movie.getId());
        values.put(title, movie.getTitle());
        values.put(overview, movie.getOverview());
        values.put(vote, movie.getVote());
        values.put(release_date, movie.getRelease_date());
        values.put(poster, movie.getPoster());

        // Inserting Row
        db.insert(movies, null, values);
        db.close(); // Closing database connection
    }

    // Getting single movie
    public Movie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String [] coulmns = {this.id,title,overview,vote,release_date,poster};
        Cursor cursor = db.query(movies, coulmns, id + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Movie movie = new Movie(cursor.getString(2),cursor.getString(1),cursor.getString(0),
                cursor.getString(5),Double.parseDouble(cursor.getString(3)) ,cursor.getString(4));
        // return movie
        return movie;
    }

    // Getting All movies
    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + movies;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getString(0));
                movie.setOverview(cursor.getString(2));
                movie.setTitle(cursor.getString(1));
                movie.setVote(Double.parseDouble(cursor.getString(3)));
                movie.setPoster(cursor.getString(5));
                movie.setRelease_date(cursor.getString(4));
                // Adding contact to list
                movieList.add(movie);
            } while (cursor.moveToNext());
        }

        // return contact list
        return movieList;
    }

    // Deleting single Movie
    public void deleteMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(movies, id + " = ?",
                new String[] { String.valueOf(1) });
        db.close();
    }
}
