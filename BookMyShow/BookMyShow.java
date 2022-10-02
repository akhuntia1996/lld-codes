import java.util.*;

public class BookMyShow {

    public static void main(String args[]){
        
        // intialization ..
        MovieService movieService = new MovieService();
        UserService userService = new UserService();
        TheaterService theaterService = new TheaterService();

        // PROCESSING ...

        Scanner input = new Scanner(System.in);
        
        // 1. user logic 
        User loginUser;
        User validUser = userService.doAuthenticate(loginUser.getEmail(), loginUser.getPassword());
        
        // 2. fetch Movies
        String city = input.next();
        List<Movies> movieList = movieService.fetchMovies(city);

        // 3. Selcting Movie for Booking
        Movie selectedMovie;
        List<Theater> theaterList = theaterService.getTheaters(city, selectedMovie);
        
        // 4. Choose theater
        Theater selectedTheater;
        List<Show> shows = selectedTheater.getHalls().get(0).getShows(); // choosing the 1st hall
        
        // 5. Choose show and seats
        Show selectedShow;
        List<Seats> seats;

        // 6. doBooking
        userService.doBooking(validUser, selectedShow, seats);

        // 7. payment
        paymentService.doPayment(seats);

        // 8. Cancel Booking
        userService.cancelBooking(validUser, selectedShow, seats);

        // 9. Refund
        // ...
        
    }
}