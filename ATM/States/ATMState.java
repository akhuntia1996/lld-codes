abstract class State {
    
    public State insertCard(Card card){
        System.out.println("Not allowed !!");
        return this;
    }
    
    public State validatePin(Card card, int pin){
        System.out.println("Not allowed !!");
        return this;
    }
    
    public State chooseOperations(Card card, TransactionType txnType) {
        System.out.println("Not allowed !!");
        return this;
    }

    public State cashWithdrawal(Card card, int amount) {
        System.out.println("Not allowed !!");
        return this;
    }

    public State cashDeposite(Card card, int amount) {
        System.out.println("Not allowed !!");
        return this;
    }

    public State checkBalance(Card card) {
        System.out.println("Not allowed !!");
        return this;
    }

    public State returnCard(Card card) {
        System.out.println("Not allowed !!");
        return this;
    }

}