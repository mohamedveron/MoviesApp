package Controller;

import java.util.ArrayList;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 9/16/2016.
 */
public interface DetailsParser {
    public ArrayList<Object> parseData(String data, String type) throws Exception;
}
