public class Yoga {
    private Board board = new Board();
    private int count;
    private Board[] solution;
    private int[] directions = board.getDirection();

    public Yoga() {
        count = NumberOfMoves(board);
        solution = new Board[count];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = new Board();
        }
    }

    private int NumberOfMoves(Board board) {
        int cost = 0;
        for (int x = 0; x < board.board.length; x++) {
            for (int y = 0; y < board.board.length; y++) {
                if (board.board[x][y] == 'X') {
                    cost++;
                }
            }
        }
        return cost;
    }

    public boolean finalSol(int step) {
        for (int x = 0; x < board.board.length && step <= count; x++) {
            for (int y = 0; y < board.board.length; y++) {
                if (board.board[x][y] == 'X') {
                    for (int direction : directions) {
                        if (board.move(x, y, direction)) {
                            board.copy(board, solution[step]);
//                            solution[x].print();
                            if (!((NumberOfMoves(board) == 1) && board.isOccupied(3, 3))) {
                                if (finalSol(step + 1)) return true;
                                else board.goBack(x, y, direction);
                            } else return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void path() {
        Board[] pathBoard = solution;
        for (int i = 0; i < pathBoard.length - 1; i++) {
            int count = 0;
            String to = "0";
            String from = "0";
            for (int x = 0; x < board.board.length; x++) {
                for (int y = 0; y < board.board.length; y++) {
                    if (pathBoard[i].board[x][y] == 'X' && pathBoard[i + 1].board[x][y] == 'o') {
                        if (x - 2 >= 0) {
                            if (pathBoard[i].board[x - 2][y] != pathBoard[i + 1].board[x - 2][y]) {
                                to = board.numberBoard[x - 2][y];
                            }
                        }
                        if (x + 2 <= 6) {
                            if (pathBoard[i].board[x + 2][y] != pathBoard[i + 1].board[x + 2][y]) {
                                to = board.numberBoard[x + 2][y];
                            }
                        }
                        if (y - 2 >= 0) {
                            if (pathBoard[i].board[x][y - 2] != pathBoard[i + 1].board[x][y - 2]) {
                                to = board.numberBoard[x][y - 2];
                            }
                        }
                        if (y + 2 <= 6) {
                            if (pathBoard[i].board[x][y + 2] != pathBoard[i + 1].board[x][y + 2]) {
                                to = board.numberBoard[x][y + 2];
                            }
                        }
                        if (!to.equals("0")) {
                            from = board.numberBoard[x][y];
                            count++;
                            break;
                        }
                    }
                    if (count == 1) break;
                }
                if (count == 1) break;
            }
            solution[i].print();
            System.out.println(i + 1 + ")\t\t  \u001B[34m(" + from + "," + to + ")\u001B[0m ");
            if (i == pathBoard.length - 2) solution[i + 1].print();
        }
    }
}

