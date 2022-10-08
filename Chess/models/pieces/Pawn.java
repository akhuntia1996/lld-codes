public class Pawn extends Jump {

    private static int countMoves;

    public Pawn(){ 
        countMoves = 0;
    }

    @Override
    public List<Integer> getAllPossiblePosition(Player player){
        // Using the rule get the list of positions
        List<Integer> endPositions;
        
        return endPositions;
    }

    public Jump promotion(){
        // return a jump piece 
    }


    /*
    @Override
    public boolean move(Player player,int endPosition){

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

    */

    
}