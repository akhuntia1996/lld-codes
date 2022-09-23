abstract class State {
    
    public State insertCard(Card card){
        System.out.println("Not allowed !!");
        return this;
    }
    
    public State insertPin(int pin){
        System.out.println("Not allowed !!");
        return this;
    }
    
    public State showOperations();
}