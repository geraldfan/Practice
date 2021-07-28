/**
 * Class handling the logic behind the game
 */
public class Logic {
    private char[][] board;
    private char[] player;
    private int currentPlayer;
    private boolean isOngoing;

    public Logic() {
        this.board = new char[][]{
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
        };
        this.player = new char[]{'x', 'o'};
        this.isOngoing = true;
    }

    /**
     * Returns the current state of the board
     * @return
     */
    public char[][] getBoard() {
        return this.board;
    }

    /**
     * Makes a move on the board
     * @param move move as an int from 1-9
     * @param player player as a char x/o
     */
    public void makeMove(int move, char player) {
        if (move <= 3) {
            this.board[0][getColumn(move)] = player;
        } else if (move <= 6) {
            this.board[2][getColumn(move)] = player;
        } else if (move <= 9) {
            this.board[4][getColumn(move)] = player;
        }
    }

    /**
     * Helper function to get the column
     * @param move move as an int from 1-9
     * @return column of the move
     */
    private int getColumn(int move) {
        int column = -1;
        move = move % 3;
        if (move == 0) {
            column = 4;
        } else if (move == 1) {
            column = 0;
        } else if (move == 2) {
            column = 2;
        }
        return column;
    }

    /**
     * Sets the currentPlayer
     * @param player char representing player
     */
    public void setPlayer(char player) {
        if (player == 'x') {
            currentPlayer = 0;
        } else if (player == 'o') {
            currentPlayer = 1;
        }
    }

    /**
     * Switches the player
     */
    public void switchPlayer() {
        if (currentPlayer == 0) {
            currentPlayer = 1;
        } else {
            currentPlayer = 0;
        }
    }

    /**
     * Returns the current player
     * @return current player as a char
     */
    public char getPlayer() {
        return player[currentPlayer];
    }

    /**
     * Returns whether the game is still ongoing
     * @return isOngoing
     */
    public boolean isOngoing() {
        return isOngoing;
    }

    /**
     * Checks for winning condition
     */
    public void checkForWin() {
        for (int i = 0; i < 5; i += 2) {
            if (board[i][0] == board[i][2] && board[i][2] == board[i][4] && (board[i][0] == player[0] || board[i][0]
                == player[1])) {
                isOngoing = false;
            }
            if (board[0][i] == board[2][i] && board[2][i] == board[4][i] && (board[0][i] == player[0] || board[0][i]
                == player[1])) {
                isOngoing = false;
            }
        }
        if (board[0][0] == board[2][2] && board[2][2] == board[4][4]&& (board[0][0] == player[0] || board[0][0]
            == player[1])) {
            isOngoing = false;
        }
        if (board[0][4] == board[2][2] && board[2][2] == board[4][0]&& (board[2][2] == player[0] || board[2][2]
            == player[1])) {
            isOngoing = false;
        }
    }
}
