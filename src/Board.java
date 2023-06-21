import java.util.Arrays;

public class Board {

    private static final char EMPTY = 'o';
    private static final char OCCUPIED = 'X';
    private static final int RIGHT = 0;
    private static final int UP = 1;
    private static final int LEFT = 2;
    private static final int DOWN = 3;
    private static int[] direction = {RIGHT, UP, LEFT, DOWN};

    public int[] getDirection() {
        return Arrays.copyOf(direction, direction.length);
    }

    char[][] board = {
                {'-', '-', 'X', 'X', 'X', '-', '-'},
                {'-', '-', 'X', 'X', 'X', '-', '-'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'o', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'-', '-', 'X', 'X', 'X', '-', '-'},
                {'-', '-', 'X', 'X', 'X', '-', '-'}

//            {'-', '-', 'o', 'o', 'o', '-', '-'},
//            {'-', '-', 'o', 'X', 'o', '-', '-'},
//            {'o', 'o', 'X', 'X', 'X', 'o', 'o'},
//            {'o', 'o', 'o', 'X', 'o', 'o', 'o'},
//            {'o', 'o', 'o', 'X', 'o', 'o', 'o'},
//            {'-', '-', 'o', 'o', 'o', '-', '-'},
//            {'-', '-', 'o', 'o', 'o', '-', '-'}

//            {'-', '-', 'o', 'o', 'o', '-', '-'},
//            {'-', '-', 'o', 'X', 'o', '-', '-'},
//            {'o', 'o', 'o', 'X', 'o', 'o', 'o'},
//            {'o', 'o', 'o', 'o', 'o', 'o', 'o'},
//            {'o', 'o', 'o', 'o', 'o', 'o', 'o'},
//            {'-', '-', 'o', 'o', 'o', '-', '-'},
//            {'-', '-', 'o', 'o', 'o', '-', '-'}

    };
    String[][] numberBoard = {
            {"A7", "B7", "C7", "D7", "E7", "F7", "G7"},
            {"A6", "B6", "C6", "D6", "E6", "F6", "G6"},
            {"A5", "B5", "C5", "D5", "E5", "F5", "G5"},
            {"A4", "B4", "C4", "D4", "E4", "F4", "G4"},
            {"A3", "B3", "C3", "D3", "E3", "F3", "G3"},
            {"A2", "B2", "C2", "D2", "E2", "F2", "G2"},
            {"A1", "B1", "C1", "D1", "E1", "F1", "G1"}
    };

    private int getNewX(int x, int direction) {
        int newX = x;
        if (direction == RIGHT) newX += 2;
        else if (direction == LEFT) newX -= 2;
        return newX;
    }

    private int getNewY(int y, int direction) {
        int newY = y;
        if (direction == UP) newY -= 2;
        else if (direction == DOWN) newY += 2;
        return newY;
    }

    private boolean isValidMove(int x, int y, int newX, int newY) {
        return 0 <= x && x < board.length &&
                0 <= y && y < board[x].length &&
                0 <= newX && newX < board.length &&
                0 <= newY && newY < board[newX].length &&
                board[newX][newY] == EMPTY &&
                board[(x + newX) / 2][(y + newY) / 2] == OCCUPIED &&
                board[x][y] == OCCUPIED;
    }

    public boolean jump(int x, int y, int direction) {
        int newX = getNewX(x, direction);
        int newY = getNewY(y, direction);
        if (isValidMove(x, y, newX, newY)) {
            board[newX][newY] = OCCUPIED;
            board[x][y] = EMPTY;
            board[(x + newX) / 2][(y + newY) / 2] = EMPTY;

            return true;
        }
        return false;
    }

    public void copy(Board source, Board target) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                target.board[x][y] = source.board[x][y];
            }
        }
    }

    public boolean isOccupied(int x, int y) {
        return board[x][y] == OCCUPIED;
    }

    public void goBack(int x, int y, int direction) {
        int newX = getNewX(x, direction);
        int newY = getNewY(y, direction);
        board[newX][newY] = EMPTY;
        board[x][y] = OCCUPIED;
        board[(x + newX) / 2][(y + newY) / 2] = OCCUPIED;
    }

    public void print() {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        System.out.print("\t");
        for (int i = 0; i < letters.length; i++) {
            System.out.printf("%-3s", letters[i]);
        }
        System.out.println();
        for (int x = 0; x < board.length; x++) {
            System.out.print(board.length-x + " ");
            for (int y = 0; y < board.length; y++) {
                System.out.printf("%3s", board[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
