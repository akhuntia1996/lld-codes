public class Booking {

    private int id;
    private Show show;
    private List<Seat> seats;
    private Payment payment;


    public void doBooking() {
        if(userRole == UserRole.GUEST){
            System.out.println("Not allowed");
            return;
        }
    }

    public void cancelBooking() {
        if(userRole == UserRole.GUEST){
            System.out.println("Not allowed");
            return;
        }
    }
}