public class Ladder extends Jump {

    public Ladder() {}

    public Ladder(int start, int end){
        super(start, end);
    }

    @Override
    public int move() {
        return start;
    }
}