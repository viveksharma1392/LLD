package model;

public class Movie extends Event {
    private String language;
    public Movie(String movieId, String title, String genre, int duration, String language) {
        super(movieId, title, genre, duration);
        this.language = language;
    }
    public String getLanguage() {
        return language;
    }
}
