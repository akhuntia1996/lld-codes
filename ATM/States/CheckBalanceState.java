class CheckBalanceState extends ATMState {

    public CheckBalanceState() {}

    @Override
    public State checkBalance(Card card) {
        System.out.println("Avaliable Balance : " + card.getBalance());
        return new IdleState();
    }
}