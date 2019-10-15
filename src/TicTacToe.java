//code founded and copied from ntu.edu.sp
//non object oriented tic tac toe example

import java.util.Scanner;


public class TicTacToe {
    //seed and cell contents
    public static final int EMPTY = 0;
    public static final int X = 1;
    public static final int O = 2;

    //name constants represents various states of the game
    public static final int PLAYING = 0;
    private static final int DRAW = 1;
    private static final int X_WON = 2;
    private static final int O_WON = 3;

    //game board and game status
    public static final int ROWS = 3, COLS = 3;//#rs of rows and colloms
    public static int [][] board = new int[ROWS][COLS];//game board in 2d array
                                                       //contains empty x o
    public static int currentState;//currentstate of the game
                                   //playing draw x won, o won
    public static int currentPlayer;//current player x or o
    public static int currentRow, currentCol;//current seeds row and collumn

    public static Scanner in = new Scanner(System.in);//the input scanner

    /**the entry main method starts here */
    public static void main(String[] args){
        System.out.println("________ ________ ________   ________ ________ ________   _________ _________ ________\n" +
                           "   |        |     |              |    |      | |               |    |       | |       \n" +
                           "   |        |     |              |    |______| |               |    |       | |_____  \n" +
                           "   |     ___|____ |_______       |    |      | |_______        |    |_______| |_______\n ");

        printBoard();
        do {
            //inits game board and current status
            initGame();
            //play the game once
            do {
                playerMove(currentPlayer);//update current row and col
                updateGame(currentPlayer, currentRow, currentCol);//update current state
                printBoard();
                //print message if game over
                if (currentState == X_WON) {
                    System.out.println("''X' Won! YEYE!");
                } else if (currentState == O_WON) {
                    System.out.println("'O' Won! YEYE!");
                } else if (currentState == DRAW) {
                    System.out.println("Congrats you both suck!");
                }
                //switch player
                currentPlayer = (currentPlayer == X) ? O : X;
            } while (currentState == PLAYING);//repeat if not game over
            System.out.print("Play again (y/n)? ");
            char ans = in.next().charAt(0);
            if  (ans != 'y' && ans != 'Y'){
                System.out.println("Good Game!");
                System.exit(0);
            }
        }while (currentState == X_WON || currentState == O_WON || currentState == DRAW);
    }
    /**init game board contents and current states*/
    public static void initGame(){
        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                board[row][col] = EMPTY;
            }
        }
        currentState = PLAYING;//ready to play
        currentPlayer = X;//x plays first
    }
    /**player with the seed makes one move, withinput validation.
     * Update global variable 'currentRow' and curentCol.*/
    public static void playerMove(int theSeed){
        boolean validInput = false; //iput validation
        do {
            if (theSeed == X){
                System.out.print("Player 'X', enter your move (row[1-3] column[1-3]):  ");
            }
            else{
                System.out.print("Player 'O,', enter your move (row[1-3] column[1-3]):  ");
            }
            int row  = in.nextInt() - 1;//array index starts at 0 instead of 1
            int col = in.nextInt() - 1;
            if (row >= 0 && row < ROWS && col < COLS && board[row][col] == EMPTY){
                currentRow = row;
                currentCol = col;
                board[currentRow][currentCol] = theSeed; // update game board content
                validInput = true; //input okay exit loop
            }
            else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
            }
        } while (!validInput); //repeat until input is valid
    }
    /** update th "currentState" after the player with "theSeed" has placed on
     * (currentRow, currentCol).*/
    public static void updateGame(int theSeed, int currentRow, int currentCol){
        if (hasWon(theSeed, currentRow, currentCol)) {//check if winnig move
            currentState = (theSeed == X) ? X_WON : O_WON;
        }
        else if (isDraw()) {//check for draw
            currentState = DRAW;
        }
        //otherwise no change to currentState (still PLAYING)
    }
    /**return true if it is a draw(no more empty cells)*/
    //TODO: shall declare draw if no playr can possibly win
    public static boolean isDraw(){
        for (int row = 0; row<ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                if (board[row][col] == EMPTY){
                    return false;// empty cell found not a draw, exit
                }
            }
        }
        return true; //no empty cells its a draw
    }
    /**retun true if playernwith "theSeed" has won after placing at current rw and column*
     */
    public static boolean hasWon(int theSeed, int currentRow, int currenmtCol){
        return (board[currentRow][0] == theSeed   // 3 in a row
                    && board[currentRow][1] == theSeed
                    && board[currentRow][2] == theSeed
                || board[0][currentCol] == theSeed  // 3 in a column
                    && board[1][currentCol] == theSeed
                    && board[2][currentCol] == theSeed
                || currentRow == currentCol       // 3 diagonal
                    && board[0][0] == theSeed
                    && board[1][1] == theSeed
                    && board[2][2] == theSeed
                || currentRow + currentCol == 2   // 3 oppo diagonal
                    && board[0][2] == theSeed
                    && board[1][1] == theSeed
                    && board[2][02] == theSeed);

    }
    //print game board
    public static void printBoard(){
        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                printCell(board[row][col]); // printeach cell
                if (col != COLS -1){
                    System.out.print(" | ");//print vertile partition
                }
            }
            System.out.println();
            if (row != ROWS-1){
                System.out.println("_____________"); //print horizontal partitions
            }
        }
        System.out.println();
    }
    //print cel with x or o
    public static void printCell(int content){
        switch (content) {
            case EMPTY: System.out.print("  "); break;
            case X : System.out.print(" X "); break;
            case O : System.out.print(" O "); break;
        }
    }
}









