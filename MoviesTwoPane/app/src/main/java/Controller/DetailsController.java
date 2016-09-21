package Controller;

import android.os.AsyncTask;
import android.util.Log;

import com.example.esc.moviestwopane.DetailsAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ESC on 9/16/2016.
 */
public class DetailsController {

    public DetailsController(){

    }

    public  void getTrials(DetailsAdapter adapter,String id){
        new OpenConnection(adapter,id).execute("https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=2922ec7e1ac93c6fbf0acbeb1ba6cea6",id);
    }

    public class OpenConnection extends AsyncTask<String,Void, ArrayList<Object>> {
        public DetailsAdapter adapter;
        public String movieId;
        public OpenConnection(DetailsAdapter adapter,String movieId){
            this.adapter = adapter;
        }

        @Override
        protected ArrayList<Object> doInBackground(String... params) {
            URL url = null;
            HttpURLConnection connection = null;
            BufferedReader buffer = null;
            String result = null;
            try {
                url = new URL(params[0]);
                movieId = params[1];
                connection = (HttpURLConnection) url.openConnection();
                InputStream input = connection.getInputStream();
                buffer = new BufferedReader(new InputStreamReader(input));
                StringBuffer sbuffer = new StringBuffer();
                String  line;
                while ((line = buffer.readLine())!= null){
                    sbuffer.append(line);
                }
                result = sbuffer.toString();
                DetailsParser parser = new DetailsJson();
                ArrayList<Object>details = parser.parseData(result,"trailers");
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
        protected void onPostExecute(ArrayList<Object>details){
            //listener.updateGridView(adapter,details);
            new Task(adapter,details).execute(movieId);
        }
    }

    public class Task extends AsyncTask<String,Void, ArrayList<Object>> {

        public DetailsAdapter adapter;
        public ArrayList<Object>trailers;
        public Task(DetailsAdapter adapter, ArrayList<Object> trailers) {
            this.adapter = adapter;
            this.trailers = trailers;
        }

        @Override
        protected ArrayList<Object> doInBackground(String... params) {
            // put task here
            URL url = null;
            HttpURLConnection connection = null;
            BufferedReader buffer = null;
            String result = null;
            String id = null;
            try {
                id = params[0];
                url = new URL("https://api.themoviedb.org/3/movie/"+id+"/reviews?api_key=2922ec7e1ac93c6fbf0acbeb1ba6cea6");
                connection = (HttpURLConnection) url.openConnection();
                InputStream input = connection.getInputStream();
                buffer = new BufferedReader(new InputStreamReader(input));
                StringBuffer sbuffer = new StringBuffer();
                String  line;
                while ((line = buffer.readLine())!= null){
                    sbuffer.append(line);
                }
                result = sbuffer.toString();
                DetailsParser parser = new DetailsJson();
                ArrayList<Object>details = trailers;
                ArrayList<Object>data = parser.parseData(result,"reviews");
                for(Object o : data)
                    details.add(o);

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
        protected void onPostExecute(ArrayList<Object> result) {
            super.onPostExecute(result);
            adapter.clear();
            for (Object o : result) {
               adapter.add(o);

                //adapter.notifyDataSetChanged();
            }

        }
    };
}
