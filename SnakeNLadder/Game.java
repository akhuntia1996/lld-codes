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
        players = new LinkedList<>();

        // Play game ...

        while(checkWinner()){
            Player currentPlayer = players.remove();

            int sum = rollDice(dice);
            int newPosition = currentPlayer.getPosition() + sum;
            if(newPosition > 99){
                players.add(currentPlayer);
                continue;
            }

            // check for snake or ladder...
            int row = newPosition / 10;
            int col = newPosition % 10;
            Jump jump = board.getCells(row, col);
            if(jump != null)
                if(jump.getStart() == newPosition)
                    newPosition = jump.getEnd();
            }

            players.add(currentPlayer);
        }

        System.out.println("Winner : " + getWinnerName());
    } 

    public int rollDice(int dice){

        int sum = 0;
        for(int i=0;i<dice;i++){
            sum += dice.rollDice();
        }

        return sum;
    }

    public boolean checkWinner(){
        for(Player player:players){
            if(player.getPosition() == 99)
                return false;
        }
        return true;
    }

    public String getWinnerName(){
        for(Player player:players){
            if(player.getPosition() == 99)
                return player.getName();
        }
        return "";
    }
}