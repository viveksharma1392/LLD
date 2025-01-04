package model;


public class Event {
    private String eventId;
    private String title;
    private String genre;
    private int duration; // in minutes

    public Event(String eventId, String title, String genre, int duration) {
        this.eventId = eventId;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public String getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }
}

