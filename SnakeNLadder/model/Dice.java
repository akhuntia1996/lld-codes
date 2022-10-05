import java.util.*;

public class Dice {

    int faces;

    public int rollDice(){
        Random random = new Random();
        return random.nextInt(faces+1);
    }
}