import java.util.*;

public class Dice {

    int faces;

    public int generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(faces);
    }
}