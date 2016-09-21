package Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Beans.Movie;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 7/28/2016.
 */
public class JsonParser implements DataParser {
    public JsonParser(){

    }

    @Override
    public ArrayList<Movie> parseData(String data) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject bigObject = new JSONObject(data);
        JSONArray array = bigObject.getJSONArray("results");
        for(int i=0;i<array.length();i++) {
            JSONObject Json = array.getJSONObject(i);
            Movie comp = new Movie();
            comp.setId(Json.getString("id"));
            comp.setOverview(Json.getString("overview"));
            comp.setPoster(Json.getString("poster_path"));
            comp.setVote(Json.getDouble("vote_average"));
            comp.setTitle(Json.getString("title"));
            comp.setRelease_date(Json.getString("release_date"));
            movies.add(comp);
        }

        return movies;
    }
}
