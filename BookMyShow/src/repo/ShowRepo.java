package repo;

import model.SeatLayout;
import model.Show;
import service.UniqueIdGenerator;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ShowRepo {
    Map<String, Show> showMap = new HashMap<String, Show>();

    private static ShowRepo showRepo;
    public static ShowRepo getInstance(){
        if(showRepo==null){
            showRepo = new ShowRepo();
        }
        return showRepo;
    }
    public Show getShowById(String showId){
        return showMap.get(showId);
    }

    public Show addShow(String showTimeId, LocalTime time, String eventId, String theaterId){
        //TODO: pull event from event id and venue from event and seat layout from venue and replicate it for below shoe
        //TODO: new SeatLayout() will not work we need to pass seat layout as per show requirement
        Show show = new Show(String.valueOf(UniqueIdGenerator.getNextId()), time,eventId,theaterId, new SeatLayout());
        showMap.put(show.getShowTimeId(), show);
        return show;
    }

    public List<Show> getAllShowsByVenue(String venueId){
        return null;
    }
    public List<Show> getAllShowsByEvent(String eventId){
        return null;
    }
}
