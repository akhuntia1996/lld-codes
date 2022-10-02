
@Service
public class MovieService {

    private Map<String, List<Movie>> movieList;
    
    public MovieService() {
        movieList = new HashMap<>();

        movieList.put("Hyd", new ArrayList<>());
        movieList.put("Bglr", new ArrayList<>());

        movieList.get("Hyd").add("RRR");
        movieList.get("Hyd").add("KGF");
        movieList.get("Bglr").add("RRR");
        movieList.get("Bglr").add("KGF");
    }

    public List<Movie> fetchMovies(User user, String city){
        return new ArrayList<>(movieList.get(city).values());
    }

    // CRUD on Movies -- Only done by Admin
}