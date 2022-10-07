public class Player {

    private int id;
    private String name;
    private PieceKind pieceKind;

    private Pawn[] pawns = new Pawn[8];
    private Rook[] rooks = new Rook[2];
    private Knight[] knights = new knights[2];
    private Bishop[] bishops = new Bishop[2];
    private Queen queen;
    private King king;

    public Player(){
        
    }
}