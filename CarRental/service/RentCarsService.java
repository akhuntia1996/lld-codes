public class RentCarsService {

    List<String, Store> stores;

    public RentCarsService(){
        // initialize stores
    }

    public List<Stores> fetchStore(String city){
        return stores.get(city);
    }

    public List<String> fetchVehicles(Store store){
        List<String> shortViewVehicle;
        List<Vehicle> vehicles = store.getVehicleInventory.getVehicles();
        for(Vehicle v:vehicles){
            shortViewVehicle.add(v.showShortViewVehicle());
        }

        return shortViewVehicle;
    }

    public String fetchVechileDetails(Vehicle vehicle){
        return vehicle.showLargeViewVehicle();
    }

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