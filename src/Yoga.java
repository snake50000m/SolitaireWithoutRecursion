import java.util.Stack;

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

    public boolean finalSol() {
        Stack<State> stack = new Stack<>();
        stack.push(new State(board, 1));
        int center;
        if (board.board.length % 2 != 0 && board.board[0].length % 2 != 0) {
            center = board.board.length / 2;
        }
        else return false;

        while (!stack.isEmpty()) {
            State currentState = stack.pop();
            Board currentBoard = currentState.board;
            int step = currentState.step;
            if (step > 1) currentBoard.copy(currentBoard, solution[step - 1]);

            for (int x = 0; x < currentBoard.board.length && step <= count; x++) {
                for (int y = 0; y < currentBoard.board.length; y++) {
                    if (currentBoard.board[x][y] == 'X') {
                        for (int direction : directions) {
                            if (currentBoard.move(x, y, direction)) {
                                currentBoard.copy(currentBoard, solution[step]);
//                                currentState.board.print();
                                if (!((NumberOfMoves(currentBoard) == 1) && currentBoard.isBusy(center, center))) {
                                    stack.push(new State(currentBoard, step + 1));
                                } else {
                                    return true;
                                }
                                currentBoard.goBack(x, y, direction);
                            }
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
                                to = board.сoordinates[x - 2][y];
                            }
                        }
                        if (x + 2 <= board.board.length - 1) {
                            if (pathBoard[i].board[x + 2][y] != pathBoard[i + 1].board[x + 2][y]) {
                                to = board.сoordinates[x + 2][y];
                            }
                        }
                        if (y - 2 >= 0) {
                            if (pathBoard[i].board[x][y - 2] != pathBoard[i + 1].board[x][y - 2]) {
                                to = board.сoordinates[x][y - 2];
                            }
                        }
                        if (y + 2 <= board.board.length - 1) {
                            if (pathBoard[i].board[x][y + 2] != pathBoard[i + 1].board[x][y + 2]) {
                                to = board.сoordinates[x][y + 2];
                            }
                        }
                        if (!to.equals("0")) {
                            from = board.сoordinates[x][y];
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

    private class State {
        private Board board;
        private int step;

        public State(Board board, int step) {
            this.board = new Board();
            this.board.copy(board, this.board);
            this.step = step;
        }
    }
}

