public class Game {
    
    private Board board;
    private Dice dice;
    private Queue<Player> players;

    public void startGame() {
        
        // input ...
        int numberOfPlayers;
        int dice;
        int snakes;
        int ladders;

        // intialization ...
        board = new Board();
        board.intialize(snakes, ladders);
        dices = new ArrayList<>();
        players = new LinkedList<>();

        // Play game ...

        while(checkWinner()){
            Player currentPlayer = players.remove();

            rollDice(dice);
        }

        System.out.println("Winner : " + getWinnerName());
    } 

    public int rollDice(int dice){

        int sum = 0;
        for(int i=0;i<dice;i++){
            sum += dic
        }
    }

    public boolean checkWinner(){
        for(Player player:players){
            if(player.getPosition() == 99)
                return true;
        }
        return false;
    }

    public String getWinnerName(){
        for(Player player:players){
            if(player.getPosition() == 99)
                return player.getName();
        }
        return "";
    }
}