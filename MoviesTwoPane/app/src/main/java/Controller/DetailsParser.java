package Controller;

import java.util.ArrayList;

import Beans.Movie;

/**
 * Created by ESC on 9/16/2016.
 */
public interface DetailsParser {
    public ArrayList<Object> parseData(String data , String type) throws Exception;
}
