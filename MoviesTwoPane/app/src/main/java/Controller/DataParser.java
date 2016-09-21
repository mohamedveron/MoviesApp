package Controller;


import java.util.ArrayList;

import Beans.Movie;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed  on 8/4/2016.
 */
public interface DataParser {
    public ArrayList<Movie> parseData(String data) throws Exception;
}
