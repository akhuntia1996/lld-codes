public enum TransactionType {
    CASH_WITHDRAW,
    CASH_DEPOSITE,
    CHECK_BALANCE

    public void showAllTransactionType(){
        int count = 1;
        for(TransactionType txn:TransactionType.values()){
            System.out.println("Enter " + count + " " + txn.name());
            count++;
        }
    }
}