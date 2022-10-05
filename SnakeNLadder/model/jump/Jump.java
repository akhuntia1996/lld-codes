public abstract class Jump {

    private int start;
    private int end;

    public Jump() {}

    public Jump(int start, int end){
        this.start = start;
        this.end = end;
    }

    public abstract void move();
}