public abstract class Jump {

    int position;
    boolean isAlive;

    public abstract boolean move(Player player, int endPosition);

    public abstract List<Integer> getAllPossiblePosition(Player player);

    public int[] convertPositionToRowColumn(int x){
        int[] rowCol = new int[2];
        rowCol[0] = x / 8;
        rowCol[1] = x % 8;
        return rowCol;
    }
}