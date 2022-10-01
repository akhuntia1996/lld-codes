import java.util.*;

class HasCardState extends State {

    public HashCardState(){}

    @Override
    public ATMState chooseOperations(){

        TransactionType txn = showOperation();
        if(txn == TransactionType.CASH_WITHDRAW)
            return new CashWithdrawalState();
        else if(txn == TransactionType.CASH_DEPOSITE)
            return new CashDepositeState();
        else if(txn == TransactionType.CHECK_BALANCE)
            return new CheckBalanceState();
        
        return new IdleState();
    }

    public TransactionType showOperation() {

        Scanner input = new Scanner(System.in);

        TransactionType.showAllOperations();
        int choose = input.nextInt();

        return getTransactionType(choose);
    }

    public TransactionType getTransactionType(int choose){
        switch(choose){
            case 1:
                return TransactionType.CASH_WITHDRAW;
            case 2:
                return TransactionType.CASH_DEPOSITE;
            case 3:
                return TransactionType.CHECK_BALANCE;
            default:
                System.out.println("Invalid Choice");
                return showOperation();
        }
    }
}