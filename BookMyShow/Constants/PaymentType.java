public enum PaymentType{

    UPI, NET_BANKING, DEBIT_CARD, CREDIT_CARD

    public void showPaymentTypes(){

        for(PaymentType paymentType:PaymentType.values()){
            System.out.println(paymentType.name());
        }
    }
}