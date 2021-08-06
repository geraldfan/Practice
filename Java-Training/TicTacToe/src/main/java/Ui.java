import java.util.Scanner;

/**
 * Class handling the ui of the program
 */
public class Ui {
    Logic logic;
    Scanner sc;

    public Ui() {
        this.logic = new Logic();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        getPlayer();
        while (logic.isOngoing()) {
            printBoard();
            getPlayerMove();
            logic.checkForWin();

            if (!logic.isOngoing()) {
                printBoard();
                if (logic.isDraw()) {
                    System.out.println("It is a draw.");
                } else {
                    System.out.println("Winner is " + logic.getPlayer());
                }
            }
            logic.switchPlayer();
        }
    }

    /**
     * Prints the current board state
     */
    private void printBoard() {
        char[][] board = logic.getBoard();

        for (char[] c : board) {
            System.out.println(c);
        }
    }

    /**
     * Gets the next player move
     */
    private void getPlayerMove() {

        System.out.println("Enter a position between 1-9");
        int move = sc.nextInt();
        if (move >= 1 && move <= 9) {
            boolean hasMoved = logic.makeMove(move, logic.getPlayer());
            if (!hasMoved) {
                System.out.println("Position is occupied, please select a new position");
                getPlayerMove();
            }
        }

    }

    /**
     * Gets the char of the first player
     */
    private void getPlayer() {
        System.out.println("Would you like to start as x/o?");
        char player = sc.next().charAt(0);
        boolean isSet = logic.setPlayer(player);
        if (!isSet) {
            System.out.println("Please only enter x/o");
            getPlayer();
        }
    }
}
