package Beans;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 9/13/2016.
 */

public class Trailer {
    private String name;
    private String id;
    private String key;

    public Trailer(){

    }

    public Trailer(String name, String id, String key) {
        this.name = name;
        this.id = id;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
