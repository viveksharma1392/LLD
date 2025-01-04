package repo;
import model.SeatLayout;

import java.util.Map;
import java.util.HashMap;

public class BookingRepo {
    //ShowId vs seat layout object which store corresponding show booking info
    Map<String, SeatLayout> bookingInfo;

    private static BookingRepo bookingRepo;
    private BookingRepo(){
        bookingInfo = new HashMap<String, SeatLayout>();
    }

    public static BookingRepo getInstance(){
        if(bookingRepo==null){
            bookingRepo = new BookingRepo();
        }
        return bookingRepo;
    }

}
