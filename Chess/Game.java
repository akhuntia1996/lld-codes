public class Game {
    
    private Board board;
    private Queue<Player> players;

    public Game(){
        board = new Board();

        Player p1 = new Player("ABC");
        Player p2 = new Player("DEF");

        players.add(p1);
        players.add(p2);
    }
    
}