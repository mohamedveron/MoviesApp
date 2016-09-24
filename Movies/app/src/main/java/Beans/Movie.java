package Beans;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 7/28/2016.
 */

// This class represents movie details
public class Movie {
    private String title;
    private  String overview;
    private String id;
    private String poster;
    private double vote;
    private String release_date;

    public Movie(){

    }

    public Movie(String overview, String title, String id, String poster, double vote,String release_date) {
        this.overview = overview;
        this.title = title;
        this.id = id;
        this.poster = poster;
        this.vote = vote;
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double  getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
