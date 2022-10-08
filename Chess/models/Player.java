public class Player extends Thread {

    private int id;
    private String name;
    private PieceKind pieceKind;
    private int countDown;

    private Pawn[] pawns = new Pawn[8];
    private Rook[] rooks = new Rook[2];
    private Knight[] knights = new knights[2];
    private Bishop[] bishops = new Bishop[2];
    private Queen queen;
    private King king;

    public Player(){}

    public Player(String name){
        countDown = 60;
        this.name = name;
    }

    public List<Integer> getAllPosition(){
        return new List<Integer>();
    }

    public void resetPieces(){
        if(pieceKind == PieceKind.BLACK){
            // intialize for black - set positions 
        } else {
            // intialize for while - set positions
        }
    }

    public void resetCountDown(){
        countDown = 60;
    }

    public void startCountDown() {
        try {
            Thread countDownThread = new Thread();
            countDownThread.start();
        } catch(Exception ee){
            ee.printStackTrace();
        }
    }

    @Override
    public void run() {
        // start counting down ...
    }
}