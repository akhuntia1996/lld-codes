// NULL Object Design Pattern for Jump class

public class NoJump extends Jump {

    @Override
    public abstract boolean move(Player player, int endPosition){
        return -1;
    }

    @Override
    public abstract List<Integer> getAllPossiblePosition(Player player){
        return null;
    }
}