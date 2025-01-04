package service;

import model.SeatLayout;
import model.Show;
import repo.ShowRepo;

import java.util.List;
import java.util.Map;

public class BookingService {
    ShowRepo showRepo;
    public SeatLayout getSeatLout(String showId){
        Show show = showRepo.getShowById(showId);
        return show.getSeatLayout();
    }

    public Boolean doBooking(String showId, String userId, List<String> seats){
        return true;
    }

    public Boolean cancelBooking(String showId, List<String> seats){
        return true;
    }
}
