package model;

import javax.swing.text.html.HTMLDocument;

public class Venue {
    private String venueId;
    private String venueName;
    private String location;

    public Venue(String venueId, String venueName, String location, SeatLayout seatLayout) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.location = location;
    }

    public String getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getLocation(){return location;}
}
