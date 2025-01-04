package model;

import java.util.ArrayList;
import java.util.List;

public class SeatLayout {
    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId, List<Seat> seats) {
        this.layoutId = layoutId;
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    private String layoutId;
    private List<Seat>  seats;
    public SeatLayout(){
        seats = new ArrayList<>();
    }
    public SeatLayout(List<Seat> seats, String layoutId){
        this.seats = seats;
        this.layoutId = layoutId;
    }
}
