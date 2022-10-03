public class Payment {

    private int id;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private double amount;

    public Payment() {}

    public Payment(PaymentType paymentType, PaymentStatus paymentStatus, Bill bill) {
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.amount = bill.getAmount();
    }

    public void doPayment() {
        // calls the payment gateway ...
    }

    // Getters and setters
}