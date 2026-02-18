/*
ATM LLD

Withdraw Flow --
User ATM Card Insert - Choose Withdrawal - Enter PIN - Get cash in dispenser

Deposite Flow --
ATM Card - Choose Deposite - PIN - Enter Cash in dispenser 

Balanace Enquiry --
ATM Card - Choose Check Balance - PIN - Show balanace in the screen

Entiry --
ATM
Card
Account
State
    Idle
    InsertCard 
    InsertPin
    
CashDispenser
Screen
KeyPad

Class --
ATM 
- Id
- Balance
- State
* doWithdraw()
* doDeposite()
* checkBalance()

State - I
// Methods for IdleState
* validateCard(Card card)
// Methods for InsertCardState
* enterPin()
// Methods for InsertPinState
* doWithdraw()
* doDeposite()
* checkBalance()

*/

import java.math.BigDecimal;
import java.time.LocalDate;

class Account {
    String accountId;
    Long balanace;
    // helper methods
}

class Card {
    String id;
    BigDecimal number;
    LocalDate expiryDate;
    int cvv;
    int pin;
    String accountId;
}

class ATM {
    int id;
    Long balance;
    Card card;

    State state;

    public ATM() {
        state = new IdleState();
        balance = 20000L;
    }

    public String main(String args[]) { // represents user
        Card card;
        int pin; 
        int amount;

        state = state.isValidCard(card);
        if(state instanceof IdleState)
            return "Invalid Card Details"; // state.getMessage();

        state = state.insertPin(card, pin);
        if(state instanceof IdleState)
            return "Invalid PIN Details";

        // Option 
        state = state.doDeposite(card, amount);

        // Option
        state = state.checkBalance(card, amount);
        if(state instanceof IdleState && state.getErrorId() == -1) {
            return state.getSuccessMessage();
        }

        // Option
        state = state.doWithdraw(card, amount, atmBalance);
        if(state instanceof IdleState)
            return "Amount Not Present";
        else {
            atmBalance -= amount;
        }

    }
}

interface State {
    public State validateCard(Card card);
    // Methods for InsertCardState
    public State enterPin();
    // Methods for InsertPinState
    public State doWithdraw();
    public State doDeposite();
    public State checkBalance();
}

class IdleState implements State {
    String message;
    public IdleState(String message) {
        this.message = message;
    }
    public State validateCard(Card card) {
        if(validCard)
            return new InsertCardState();
        else
            return new IdleState();
    }

    // Other methods are invalid use case
}

class InsertCardSate implements State {
    String message;
    public IdleState(String message) {
        this.message = message;
    }
    public State insertPin(Card card, int pin) {
        if(validPin) 
            return new ValidPinState();
        else
            return new IdleState();
    }
}

class ValidPinState implements State {
    String message;
    public IdleState(String message) {
        this.message = message;
    }
    public State doWithdraw(Card card, int amount, int atmBalance) {
        int balanace = card.getAccount().getBalance();
        if(amount > balanace)
            return new IdleState("Invalid Balance");
        else if(atmBalance < amount)
            return new IdleState("Insufficent Balance");
        else {
            balanace -= amount;
            card.getAccount().setBalance(balanace);
            return new CashDispenserState(amount);
        }
    }
}

/*
Two ATMs Withdraw Same Account --
- Put Optmistic Lock (okay to read)
- Maintain atomicity in banking service

Cash Dispensed But Account Not Debited
- Saga Design
- Distribute txn

Cash Jam Scenario
- Make transactions status PENDING
- Check time out 
- Refund if needed

How do you make withdrawal idempotent?
- TransactionId
- Bank ignores duplicates
*/
