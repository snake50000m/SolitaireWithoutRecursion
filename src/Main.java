public class Main {
    public static void main(String[] args) {
        Solitaire solitaire = new Solitaire();
        long time = System.currentTimeMillis();
        if (solitaire.finalSol(1)) {
            System.out.println("Решение найдено за " + (System.currentTimeMillis() - time) / 1000 + " секунд");
            solitaire.path();
        } else {
            System.out.println("Решение не найдено");
        }

    }
}
