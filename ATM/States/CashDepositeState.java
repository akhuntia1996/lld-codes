class CashDepositeState extends ATMState {

    public CashDepositeState() {}

    @Override
    public State cashDeposite(Card card, int amount) {
        
        card.setBalance(card.getBalance() + amount);
        return new IdleState();
    } 
}