public class Solitaire {
    private Board board = new Board();
    int sol = Heuristic(board);
    private Board[] solution = new Board[sol + 1];
    private int[] directions = board.getDirection();

    public Solitaire() {
        for (int i = 0; i < solution.length; i++) {
            solution[i] = new Board();
        }
    }

    private int Heuristic(Board board) {
        int cost = 0;
        for (int x = 0; x < board.board.length; x++) {
            for (int y = 0; y < board.board.length; y++) {
                if (board.board[x][y] == 'X') {
                    cost++;
                }
            }
        }
        return cost - 1;
    }

    public boolean finalSol(int step) {
        for (int x = 0; x < board.board.length && step <= sol; x++) {
            for (int y = 0; y < board.board.length; y++) {
                for (int direction : directions) {
                    if (board.jump(x, y, direction)) {
                        board.copy(board, solution[step]);
                        solution[step].print();
                        if (!((Heuristic(board) == 0) /*&& board.isOccupied(3, 3)*/)) {
                            if (finalSol(step + 1)) return true;
                            else board.goBack(x, y, direction);
                        } else return true;
                    }
                }
            }
        }
        return false;
    }

    public void solvePuzzle() {
        System.out.println(solution.length);
        for (int i = 0; i < solution.length; i++) {
            solution[i].print();
        }
    }

    int count;
    String from, to;

    public void path() {
        Board[] pathBoard = solution;
        // for (int j = 0; j < solution.length; j++) {
        //   solution[j].print();
        for (int i = 0; i < pathBoard.length - 1; i++) {
            count = 0;
            to = "0";
            from = "0";
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
                        if (to != "0") {
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
            System.out.println(i + 1 + ")\t\t(" + from + "," + to + ") ");
            if (i == pathBoard.length - 2) solution[i + 1].print();
        }
        //}
    }
};

