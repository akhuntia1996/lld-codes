public class Game {
    
    private Board board;
    private Queue<Player> players;

    public Game(){
        board = new Board();

        Player p1 = new Player("ABC");
        p1.setPieceKind = PieceKind.BLACK;
        p1.resetPieces();
     
        Player p2 = new Player("DEF");
        p2.setPieceKind = PieceKind.WHITE;
        p2.resetPieces();

        players.add(p1);
        players.add(p2);
    }

    public void startGame() {

        // Intialization ...
        intializeCells();
        intializePieces();

        // PROCESSING ...

        while(checkKingAliveForAll()){
            Player player = players.remove();

            // 0. Start timer
            player.resetCountDown();
            player.startTime();

            // 1. Choose Piece to run
            Jump jump;

            // 2. Get all Possible positions
            List<Integer> endPositions = jump.getAllPossiblePosition(player);

            // 3. Choose 1 position out of it
            int endPosition;

            // 4. Check collision with other Jumps
            Map<Integer, Jump> otherPiecePosition = this.getPositions(player);
            for(Entry.Set <Integer, Jump> e : otherPiecePosition){
                if(e.getKey() == endPosition){
                    e.getValue().setIsAlive(false);
                    e.getValue().setPosition(-1);
                    jump.move(endPosition);
                }
            }

            // 5. Stop Timer
            player.resetCountDown();

            players.add(player);
        }

        System.out.println("Winner : " + getWinnerName());
     
    }

    public Map<Integer, Jump> getPositions(Player player){
        if(player.getPieceKind() == PieceKind.WHITE) {
            // get all black positions
        } else {
            // get all while positions
        }
    }

    public boolean checkKingAliveForAll(){
        for(Player player : players){
            if(!player.getKing().isAlive())
                players.remove(player);
        }

        return players.get(0).getName();
    }

    public boolean checkKingAliveForAll(){
        for(Player player : players){
            if(!player.getKing().isAlive())
                return false;
        }

        return true;
    }

    public void intializePieces() {

        cells[][] = board.getCells();

        int x = 2
        while(x > 0) {
            Player p = players.remove();

            // setting for pawn;
            Pawn[] pawns = p.getPawns();
            for(Pawn pawn : pawns){
                int rowCol[] = pawn.convertPositionToRowColumn(pawn.getPosition());
                cells[rowCol[0]][rowCol[1]] = pawn;
            }

            // similarly do for others ....

            players.add(p);
        }
    }

    public void intializeCells(){
        
        Cell[][] cells = new int[8][8];

        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                cells.setJump(new NoJump());

        board.setCells(cells);
    }
    
}