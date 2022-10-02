public class Booking {

    private int id;
    private Show show;
    private List<Seat> seats;
    private Payment payment;


    public void doBooking(User user, Seat seat) {
        if(user.getUserRole() == UserRole.GUEST){
            System.out.println("Not allowed");
            return;
        }

        seats.forEach( (s) -> { 
            if(s.getId == seat.getId())
                s.setIsBooked(true);
        });
    }

    public void cancelBooking(User user, Seat seat) {
        if(user.getUserRole() == UserRole.GUEST){
            System.out.println("Not allowed");
            return;
        }

        seats.forEach( (s) -> { 
            if(s.getId == seat.getId())
                s.setIsBooked(false);
        });
    }
}