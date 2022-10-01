class Idle extends State{

    public Idle(){}

    @Override
    public State insertCard(Card card) {
        System.out.println("Card Inserted ");
        
        // do card validation ...
        boolean isRightCard = card.doValidate();
        if(!isRightCard)
            return new IdleState();

        // insert pin
        Scanner input = new Scanner(System.in);
        int pin = input.nextInt();

        // do PIN validation 
        boolean isValidPin = this.validatePin(card, pin);
        if(!isValidPin)
            return new IdleState();
        
        return new HasCardState();
    }

    @Override
    public boolean validatePin(Card card, int pin){
        
        // pin validation
        return card.toPinValidation(pin);
    }

}