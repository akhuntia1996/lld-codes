import java.util.*;

/*
    This class is being hardcoded for now, it should be fetched from database
*/
public class Card {

    private int number;
    private Date expiry;
    private int cvv;
    private pin;
    private BankAccount bankAccount;

    public Card() {}

    public Card(int number, int pin, Date expiry, int cvv) {
        this.number = number;
        this.pin = pin;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    public boolean doValidate(Card card){
        if(card.getNumber() == this.number && card.getExpiry().equals(this.expiry))
            return true;

        return false;
    }

    public boolean doValidatePin(Card card, int pin){
        if(card.getCvv() == this.cvv && card.getPin() == this.pin)
            return true;

        return false;
    }

    // Other validation operation
    // Getters and setters
}