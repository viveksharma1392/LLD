package model;

import java.time.LocalTime;

public class Show {
    private String showTimeId;
    private LocalTime time;
    private String eventId;
    private String venueId;

    private SeatLayout seatLayout;

    public Show(String showTimeId, LocalTime time, String eventId, String theaterId, SeatLayout seatLayout) {
        this.showTimeId = showTimeId;
        this.time = time;
        this.eventId = eventId;
        this.venueId = theaterId;
        this.seatLayout = seatLayout;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getEvent() {
        return eventId;
    }

    public String getVenue() {
        return venueId;
    }
    public String getShowTimeId(){
        return showTimeId;
    }
     public SeatLayout getSeatLayout(){
        return seatLayout;
     }

}

