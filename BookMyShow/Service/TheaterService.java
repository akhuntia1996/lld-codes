
@Service
public class TheaterService {

    private Map<String, Theater> theaters;
    public TheaterService(){
        // Default theates/ shows/ Seats
    }

    public List<Theaters> getTheaters(String city, Movie movie){
        return new ArrayList<>(this.theaters.get(city).values());
    }

    // CRUD on theater - only by admin
}