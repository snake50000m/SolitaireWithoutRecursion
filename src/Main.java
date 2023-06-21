public class Main {
    public static void main(String[] args) {
        Solitaire solitaire = new Solitaire();
        long time = System.currentTimeMillis();
        if (solitaire.finalSol(1)) {
            System.out.println("Решение найдено за " + (System.currentTimeMillis() - time) / 1000 + " секунд");
            //solitaire.solvePuzzle();
            solitaire.path();
        } else {
            System.out.println("Решение не найдено");
        }

//        Board board = new Board();
//        for (int x = 0; x < board.board.length; x++) {
//            for (int y = 0; y < board.board.length; y++) {
//                System.out.printf("%3s", board.board[x][y]);
//            }
//            System.out.println();
//        }
    }
}