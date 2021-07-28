import java.util.Scanner;

public class Ui {
    Logic logic;
    Scanner sc;
    public Ui() {
        this.logic = new Logic();
        this.sc = new Scanner(System.in);
    }
    public void run() {
        getPlayer();
        printBoard();
        getPlayerMove();
        printBoard();
    }

    private void printBoard() {
        char[][] board = logic.getBoard();

        for (char[] c : board) {
            System.out.println(c);
        }
    }

    private void getPlayerMove() {
        System.out.println("Enter a position between 1-9");
        int move = sc.nextInt();
        logic.makeMove(move, logic.getPlayer());
    }

    private void getPlayer() {
        System.out.println("Would you like to start as x/o?");
        char player = sc.next().charAt(0);
        logic.setPlayer(player);
    }
}
