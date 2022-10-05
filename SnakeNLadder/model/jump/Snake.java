public class Snake extends Jump {

    public Snake(){}

    public Snake(int start, int end){
        super(start, end);
    }

    @Override
    public int move() {
        return this.getEnd();
    }
}