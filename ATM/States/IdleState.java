class Idle extends State{

    public Idle(){}

    @Override
    public State insertCard(Card card) {
        System.out.println("Card Inserted ");
        
        // do card validation ...
        card.doValidate();
        
        return new HasCardState();
    }

}