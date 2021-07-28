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

    public char[][] getBoard() {
        return this.board;
    }

    public void makeMove(int move, char player) {
        if (move <= 3) {
            this.board[0][getColumn(move)] = player;
        } else if (move <= 6) {
            this.board[2][getColumn(move)] = player;
        } else if (move <= 9) {
            this.board[4][getColumn(move)] = player;
        }
    }

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

    public void setPlayer(char player) {
        if (player == 'x') {
            currentPlayer = 0;
        } else if (player == 'o') {
            currentPlayer = 1;
        }
    }

    public char getPlayer() {
        return player[currentPlayer];
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public void checkForWin() {
        for (int i = 0; i < 5; i += 2) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                isOngoing = false;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                isOngoing = false;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == )
    }
}
