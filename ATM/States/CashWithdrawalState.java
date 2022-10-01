class CashWithdrawalState extends State {

    public CashWithdrawalState() {}

    @Override
    public State cashWithdrawal(Card card, int amount) {
        double balance = card.getBalance();

        if(balance < amount){
            System.out.println("InSufficent Balance");
            return new IdleState();
        }

        card.setBalance(card.getBalance() - amount);
        return new IdleState();
    }
}