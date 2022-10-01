public class ATM {

    public static void main(String args[]){

        // intailization ...
        Card card = initializeCard();
        
        Bank bank = new Bank();
        BankAccount bankAccount = new BankAccount();
        // update BankAccount details

        ATMState currentState = new IdleState();

        // PROCESSING ...
        Card userCard; // Card inserted
        currentState = currentState.insertCard(userCard);
        currentState = this.chooseOperations(currentState);
        currentState = this.doOperation(currentState);

    }

    public ATMState doOperation(ATMState currentState){
        if(currentState instanceof Idle)
            System.exit(0);

        if(currentState instanceof CheckBalanceState)
            return currentState.checkBalance();
        if(currentState instanceof CashWithdrawalState)
            return currentState.cashWithdraw();
        if(currentState instanceof CashDepositeState)
            return currentState.cashDeposite();
    }

    public ATMState chooseOperations(ATMState currentState){
        if(currentState instanceof Idle)
            System.exit(0);

        return currentState.chooseOperations();
    }

    public void initializeCard(){
        return new Card(10101. 1234, new Date("23/03/2023"), 101);
    }
}