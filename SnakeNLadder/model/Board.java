public class Board {

    Cell[][] cells = new Cell[10][10];

    public void intialize(int snakes, int ladder){

        Random random = new Random();

        while(snakes > 0){

            int start = random.nextInt(100);
            int end = random.nextInt(100);
            if(start <= end)
                continue;

            int startRow = start / 10;
            int startCol = start % 10;

            Jump jump = new Snake(start, end);
            cells[startRow][startCol] = new Cell(jump);

            snake--;
        }

        while(ladder > 0){

            int start = random.nextInt(100);
            int end = random.nextInt(100);
            if(start >= end)
                continue;

            int startRow = start / 10;
            int startCol = start % 10;

            Jump jump = new Ladder(end, start);
            cells[startRow][startCol] = new Cell(jump);

            ladder--;
        }
    }

    public Jump getCell(int row, int col){
        return cells[row][col].getJump();
    }
}