public class Bill {

    private int id;
    private String name;
    private double amount;

    public Bill() {}

    public void generateBill(Booking booking, Vechile vechile){
        this.name = booking.getName();
        this.amount = this.calculateAmount(booking.getHours(), vechile.getPricePerHour());
    }

    private double calculateAmount(int hours, int price){
        return price * hours;
    }
}