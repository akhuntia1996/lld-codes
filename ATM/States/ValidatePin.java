class ValidatePin extends State {
    
    private int pin;
    public ValidatePin(int pin){
        this.pin = pin;
    }

    public State validatePin(){
        if(isCorrect(pin))
            return showOperation();
        return Idle();
    }

    public State showOperation() {
        System.out.println("Showing Operations");
        int choice;
        // Choose One operation from menu
        return new PerformOperation(choice);
    }

}