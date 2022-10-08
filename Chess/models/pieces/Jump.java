public abstract class Jump {

    int position;
    boolean isAlive;

    public boolean move(Player player, int endPosition){
        // Algorithm 
        // 1. Convert Start Position and end position to row and cols
        // 2. Check for White (Start at bottom) or Black (Start at top)
        // 3. Check Rule
        // 4. SetPosition

        List<Integer> endPositions = this.getAllPossiblePosition(player);
        for(int x : endPositions){
            if(x == endPosition){
                this.setPosition(endPosition);
                return true;
            }
        }

        return false;
    }

    public abstract List<Integer> getAllPossiblePosition(Player player);

    public int[] convertPositionToRowColumn(int x){
        int[] rowCol = new int[2];
        rowCol[0] = x / 8;
        rowCol[1] = x % 8;
        return rowCol;
    }
}