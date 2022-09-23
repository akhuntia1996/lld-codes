class ValidateCard extends State{

    private Card card;

    public ValidateCard(){}
    public ValidateCard(Card card){
        this.card = card;
    }

    public State validateCard(){
        if(isCorrect(card.getNumber())){
            // take input pin
            int pin;
            return this.insertPin(pin);
        }
        return new Idle();
    }
    public boolean isCorrect(int cardNumber){
        if(isthere in database)
            return true;
        return false;
    }

    public State insertPin(int pin) {
        System.out.println("Pin Inserted !!");
        return new ValidatePin(pin);
    }

}