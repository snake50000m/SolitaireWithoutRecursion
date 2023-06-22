public class Main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();
        Yoga yoga = new Yoga();
        long time = System.currentTimeMillis();
        if (yoga.finalSol(1)) {
            long finalMemory = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed = finalMemory - initialMemory;
            System.out.println("Использовано памяти: " + memoryUsed + " байт");
            System.out.println("Решение найдено за " + (System.currentTimeMillis() - time) + " миллисекунд");
            yoga.path();
        } else {
            System.out.println("Решение не найдено");
        }

    }
}
