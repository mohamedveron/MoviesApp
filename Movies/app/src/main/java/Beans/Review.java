package Beans;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 9/13/2016.
 */
public class Review {
    private String content;
    private String id;
    private String author;

    public Review(String content, String id, String author) {
        this.content = content;
        this.id = id;
        this.author = author;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
