public class Pawn extends Jump {

    private int countMoves;

    public Pawn(){ 
        countMoves = 0;
    }

    @Override
    public abstract boolean move(Player player,int endPosition){

        // Algorithm 
        // 1. Convert Start Position and end position to row and cols
        // 2. Check for White (Start at bottom) or Black (Start at top)
        // 3. Check Rule
        // 3.1. If White and 1st move, Check Row for +1 and +2
        // 3.2. If Black and 1st move, Check Row for -1 and -2;
        // 3.3. If White and Other move, Check for Row +1
        // 3.4. If Black and Other move, Check for Row -1

        int[] rowColStart = this.convertPositionToRowColumn(this.startPosition());
        int[] rowColEnd = this.convertPositionToRowColumn(endPosition);

        if(this.countMoves == 0){
            if(player.getPieceKind() == PieceKind.WHITE){
                
                // for 1st move, it can move upto 2 boxes forward
                if(rowColStart[1] == rowColEnd[1]){
                    if(rowColStart[0] + 1 == rowColEnd[0] || rowColStart[0] + 2 == rowColEnd[0]){
                        this.setPosition(endPosition);
                        return true;
                    } else
                        return false;
                } else
                    return false;

            } else {

                if(rowColStart[1] == rowColEnd[1]){
                    if(rowColStart[0] - 1 == rowColEnd[0] || rowColStart[0] - 2 == rowColEnd[0]){
                        this.setPosition(endPosition);
                        return true;
                    } else
                        return false;
                } else
                    return false;

            }
            this.countMoves++;
        } else {

            if(player.getPieceKind() == PieceKind.WHITE){
                
                if(rowColStart[1] == rowColEnd[1]){
                    if(rowColStart[0] + 1 == rowColEnd[0]){
                        this.setPosition(endPosition);
                        return true;
                    } else
                        return false;
                } else
                    return false;

            } else {

                if(rowColStart[1] == rowColEnd[1]){
                    if(rowColStart[0] - 1 == rowColEnd[0]){
                        this.setPosition(endPosition);
                        return true;
                    } else
                        return false;
                } else
                    return false;

            }
        }
    }

    @Override
    public abstract List<Integer> getAllPossiblePosition(Player player){
        return null;
    }
}