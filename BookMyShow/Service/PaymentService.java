
@Service 
public class PaymentService {

    public void doPayment(List<Seats> seats){
        Payment payment = new Payment();

        double amount = 0.0;
        for(Seat s:seats)
            amount += s.getPrice();

        payment.setAmount(amount);

        //select payment type
        PaymentType.showAllPaymentTypes();
        payment.setPaymentType(PaymentType.UPI); // choosen one

        paymentStatus.setPaymentStatus(PaymentStatus.ACTIVE);
        // save in DB

        // call the payment gateway API

        paymentStatus.setPaymentStatus(PaymentStatus.PAID);

        // save in DB

    }
}