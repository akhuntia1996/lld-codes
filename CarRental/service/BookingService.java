
@Service
public class BookingService {

    public BookingService() {}

    public Booking doBooking(User user, Vehicle vehicle, int hours){
        Booking booking = new Booking();
        booking.setName(user.getName());
        booking.setVehicle(vehicle);
        booking.setHours(hours);
        
        vehicle.bookVehicle();

        return booking;
    }

    public void cancelBooking(Booking booking){
        booking.getVehicle().cancelBooking();
    }
}