package Controller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Beans.Review;
import Beans.Trailer;


/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 9/16/2016.
 */
public class DetailsJson implements DetailsParser {

    @Override
    public ArrayList<Object> parseData(String data,String type) throws Exception {
        ArrayList<Object> details = new ArrayList<>();
        JSONObject bigObject = new JSONObject(data);
        JSONArray array = bigObject.getJSONArray("results");
        for(int i=0;i<array.length();i++) {
            JSONObject Json = array.getJSONObject(i);
            if(type.equals("trailers")){
                Trailer t = new Trailer(Json.getString("name"),Json.getString("id"),Json.getString("key"));
                details.add(t);
            }
            else if(type.equals("reviews")){
                Review review = new Review(Json.getString("content"),Json.getString("id"),Json.getString("author"));
                details.add(review);
            }
        }
        return details;
    }
}
