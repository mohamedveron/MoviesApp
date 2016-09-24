package Controller;

import android.os.AsyncTask;
import android.util.Log;

import com.example.esc.movies.MovieAdapter;
import com.example.esc.movies.OnDataSendToActivity;
import Beans.Movie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 7/28/2016.
 */
public class MovieController {

    public MovieController(){

    }

    public  static void getMostPopular(OnDataSendToActivity listener,MovieAdapter adapter){
           new OpenConnection(listener,adapter).execute("https://api.themoviedb.org/3/movie/popular?api_key=2922ec7e1ac93c6fbf0acbeb1ba6cea6");
    }
    public  static void getTopRated(OnDataSendToActivity listener,MovieAdapter adapter){
        new OpenConnection(listener,adapter).execute("https://api.themoviedb.org/3/movie/top_rated?api_key=2922ec7e1ac93c6fbf0acbeb1ba6cea6");
    }
    public  static void getTrials(OnDataSendToActivity listener,MovieAdapter adapter,String id){
        new OpenConnection(listener,adapter).execute("https://api.themoviedb.org/3/movie/278/reviews?api_key=2922ec7e1ac93c6fbf0acbeb1ba6cea6");
    }
    public  static void getReviews(OnDataSendToActivity listener,MovieAdapter adapter,String id){
        new OpenConnection(listener,adapter).execute("https://api.themoviedb.org/3/movie/278/videos?api_key=2922ec7e1ac93c6fbf0acbeb1ba6cea6");
    }

    public static class OpenConnection extends AsyncTask<String,Void, ArrayList<Movie>>{
        public MovieAdapter adapter;
        public OnDataSendToActivity listener;
        public OpenConnection(OnDataSendToActivity listener,MovieAdapter adapter){
             this.adapter = adapter;
            this.listener = listener;
        }

        @Override
        protected ArrayList<Movie> doInBackground(String... params) {
            URL url = null;
            HttpURLConnection connection = null;
            BufferedReader buffer = null;
            String result = null;
            try {
                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream input = connection.getInputStream();
                buffer = new BufferedReader(new InputStreamReader(input));
                StringBuffer sbuffer = new StringBuffer();
                String  line;
                while ((line = buffer.readLine())!= null){
                    sbuffer.append(line);
                }
                result = sbuffer.toString();
                DataParser parser = new JsonParser();
                ArrayList<Movie>details = parser.parseData(result);
                Log.v("here", result);
                return details;
            } catch (MalformedURLException e) {
                System.out.println("URL is wrong");
            } catch (IOException e) {
                System.out.println("Connection failed");
            } catch (Exception e) {
                System.out.println("PARSE JSON failed");
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie>details){
            listener.updateGridView(adapter,details);

        }
    }
}
