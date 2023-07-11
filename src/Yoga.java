import java.util.Stack;

public class Yoga {
    private Board board = new Board();
    private int count;
    private Move[] solution;
    private int[] directions = board.getDirection();

    public Yoga() {
        count = NumberOfMoves(board);
        solution = new Move[count - 1];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = new Move("D2", 3);
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
        stack.push(new State(board, 0, "D2", 3));
        int center;
        if (board.board.length % 2 != 0 && board.board[0].length % 2 != 0) {
            center = board.board.length / 2;
        } else return false;

        while (!stack.isEmpty()) {
            State currentState = stack.pop();
            Board currentBoard = currentState.board;
            int step = currentState.step;
            if (step > 0) {
                solution[step - 1].setCellNumber(currentState.cellNumber);
                solution[step - 1].setDirection(currentState.direction);
            }

            for (int x = 0; x < currentBoard.board.length && step <= count; x++) {
                for (int y = 0; y < currentBoard.board.length; y++) {
                    if (currentBoard.board[x][y] == 'X') {
                        for (int direction : directions) {
                            if (currentBoard.move(x, y, direction)) {
                                solution[step].setCellNumber(currentBoard.сoordinates[x][y]);
                                solution[step].setDirection(direction);
                                if (!((NumberOfMoves(currentBoard) == 1) && currentBoard.isBusy(center, center))) {
                                    stack.push(new State(currentBoard, step + 1, currentBoard.сoordinates[x][y], direction));
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
        System.out.println();
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i].toString());
        }
        System.out.println();
        char[][] result = board.board;
        String[][] coordinates = board.сoordinates;
        String to = "0";
        String from = "0";

        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        System.out.print("\t");
        for (int z = 0; z < letters.length; z++) {
            System.out.printf("\u001B[31m%-3s\u001B[0m", letters[z]);
        }
        System.out.println();
        for (int j = 0; j < board.board.length; j++) {
            System.out.print("\u001B[31m" + (j + 1) + "\u001B[0m ");
            for (int k = 0; k < board.board[j].length; k++) {
                System.out.printf("%3s", board.board[j][k]);
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < solution.length; i++) {
            for (int x = 0; x < board.board.length; x++) {
                for (int y = 0; y < board.board.length; y++) {
                    if (solution[i].getCellNumber() == coordinates[x][y]) {
                        from = solution[i].getCellNumber();
                        if (solution[i].getDirection() == 0) {
                            result[x][y + 2] = 'X';
                            result[x][y] = 'o';
                            result[x][y + 1] = 'o';
                            to = coordinates[x][y + 2];
                        } else if (solution[i].getDirection() == 1) {
                            result[x - 2][y] = 'X';
                            result[x][y] = 'o';
                            result[x - 1][y] = 'o';
                            to = coordinates[x - 2][y];
                        } else if (solution[i].getDirection() == 2) {
                            result[x][y - 2] = 'X';
                            result[x][y] = 'o';
                            result[x][y - 1] = 'o';
                            to = coordinates[x][y - 2];
                        } else if (solution[i].getDirection() == 3) {
                            result[x + 2][y] = 'X';
                            result[x][y] = 'o';
                            result[x + 1][y] = 'o';
                            to = coordinates[x + 2][y];
                        }
                    }
                }
            }
            System.out.println(i + 1 + ")\t\t  \u001B[34m(" + from + "," + to + ")\u001B[0m ");
            System.out.print("\t");
            for (int z = 0; z < letters.length; z++) {
                System.out.printf("\u001B[31m%-3s\u001B[0m", letters[z]);
            }
            System.out.println();
            for (int j = 0; j < result.length; j++) {
                System.out.print("\u001B[31m" + (j + 1) + "\u001B[0m ");
                for (int k = 0; k < result[j].length; k++) {
                    System.out.printf("%3s", result[j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private class State {
        private Board board;
        private int step;
        private String cellNumber;
        private int direction;

        public State(Board board, int step, String cellNumber, int direction) {
            this.board = new Board();
            this.board.copy(board, this.board);
            this.step = step;
            this.cellNumber = cellNumber;
            this.direction = direction;
        }
    }

    public class Move {
        private String cellNumber;
        private int direction;

        public Move(String cellNumber, int direction) {
            this.cellNumber = cellNumber;
            this.direction = direction;
        }

        public String getCellNumber() {
            return cellNumber;
        }

        public int getDirection() {
            return direction;
        }

        public void setCellNumber(String cellNumber) {
            this.cellNumber = cellNumber;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Номер ячейки = " + cellNumber + ", Направление = " + direction;
        }
    }

}
