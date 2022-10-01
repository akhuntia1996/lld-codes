class CashWithdrawalState extends State {

    public CashWithdrawalState() {
    }

    @Override
    public State cashWithdrawal(Card card, int amount) {
        double balance = card.getBalance();

        if(balance < amount){
            System.out.println("InSufficent Balance");
            return new IdleState();
        }

        // You can also Apply chain Responsibliy Design Pattern here ...
        // counting notes ...
        Notes notes = new Notes();
        Notes notesWithdraw = notes.getNotesForWithdrawal(int amount);
        if(notesWithdraw == null){
            System.out.println("Notes not avaliable");
            return new IdleState();
        }

        card.setBalance(card.getBalance() - amount);

        return new IdleState();
    }
}