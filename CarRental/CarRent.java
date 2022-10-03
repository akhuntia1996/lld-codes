public class CarRent {

    public static void main(String args[]){

        // Initiliaztion
        RentCarService rentCarService = new RentCarService();

        // PROCESSING ...

        // 1. User Login
        User user = new User("Abhishek");

        // 2. Enter City and fetch Store based on city
        String city = "Hyderabad";
        rentCarService.fetchStores(city);

        // 3. Fetch Vehicles of a stores
        Store store; // Choosen from above list
        rentCarService.fetchVehiles(store);

        // 4. Fetch all details of a vehicle
        Vehicle vehicle; 
        rentCarService.fetchVechileDetails(vehicle);

        // 5. Book that vechile
        int hours;
        Booking booking = rentCarService.doBooking(user, vehicle, hours);

        // 6. Generate Bill
        Bill bill = new Bill();
        bill.generateBill(booking, vehicle);
        booking.setBill(bill);

        // 7. Payment
        Payment payment = new Payment(PaymentType.UPI, PaymentStatus.ACTIVE, bill);
        payment.doPayment();

        // 8. Cancel Booking
        rentCarService.cancelBooking(booking);

        // 9. Refund
        // ...
    }
}