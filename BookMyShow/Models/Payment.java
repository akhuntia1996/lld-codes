public class Payment {

    private int id;
    private PaymentType paymentType;
    private int amount;
    private PaymentStatus paymentStatus;

    public void doPayment() {
        if(userRole == UserRole.GUEST){
            System.out.println("Not allowed");
            return;
        }
    }

}