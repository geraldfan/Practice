/**
 * Class handling the logic behind the game
 */
public class Logic {
    private char[][] board;
    private char[] player;
    private int currentPlayer;
    private boolean isOngoing;
    private boolean isFull;

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
        this.isFull = true;
    }

    /**
     * Returns the current state of the board
     *
     * @return a char arrau representation of the board
     */
    public char[][] getBoard() {
        return this.board;
    }

    /**
     * Makes a move on the board
     *
     * @param move   move as an int from 1-9
     * @param player player as a char x/o
     */
    public boolean makeMove(int move, char player) {
        if (move <= 3) {
            if (!checkOccupied(0, getColumn(move))) {
                this.board[0][getColumn(move)] = player;
                return true;
            }
        } else if (move <= 6) {
            if (!checkOccupied(2, getColumn(move))) {
                this.board[2][getColumn(move)] = player;
                return true;
            }
        } else if (move <= 9) {
            if (!checkOccupied(4, getColumn(move))) {
                this.board[4][getColumn(move)] = player;
                return true;
            }
        }
        return false;
    }

    private boolean checkOccupied(int row, int column) {
        if (board[row][column] == player[0] || board[row][column] == player[1]) {
            return true;
        }

        return false;
    }

    /**
     * Helper function to get the column
     *
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
     *
     * @param player char representing player
     */
    public boolean setPlayer(char player) {
        if (player == 'x') {
            currentPlayer = 0;
            return true;
        } else if (player == 'o') {
            currentPlayer = 1;
            return true;
        }
        return false;
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
     *
     * @return current player as a char
     */
    public char getPlayer() {
        return player[currentPlayer];
    }

    /**
     * Returns whether the game is still ongoing
     *
     * @return isOngoing
     */
    public boolean isOngoing() {
        return isOngoing;
    }

    /**
     * Checks for winning condition
     */
    public void checkForWin() {
        // Checks rows and columns
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

        // Checks diagonals
        if (board[0][0] == board[2][2] && board[2][2] == board[4][4] && (board[0][0] == player[0] || board[0][0]
            == player[1])) {
            isOngoing = false;
        }
        if (board[0][4] == board[2][2] && board[2][2] == board[4][0] && (board[2][2] == player[0] || board[2][2]
            == player[1])) {
            isOngoing = false;
        }
        //Checks if grid is full
        this.isFull = true;
        for (int i = 0; i < 5; i += 2) {
            for (int j = 0; j < 5; j += 2) {
                if (board[i][j] != player[0] && board[i][j] != player[1]) {
                    isFull = false;
                    break;
                }
            }
        }

        if (isFull) {
            isOngoing = false;
        }
    }

    public boolean isDraw() {
        if (isFull) {
            return true;
        }

        return false;
    }
}
