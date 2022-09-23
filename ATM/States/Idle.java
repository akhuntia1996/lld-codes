class Idle extends State{

    public Idle(){

    }

    public State insertCard(Card card) {
        System.out.println("Card Inserted ");
        return new ValidateCard(card);
    }

}