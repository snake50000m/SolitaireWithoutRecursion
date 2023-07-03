public class Main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();
        Yoga yoga = new Yoga();
        long initialTime = System.currentTimeMillis();
        if (yoga.finalSol()) {
            long finalMemory = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed = finalMemory - initialMemory;
            float floatMemory = memoryUsed;
            long finalTime = System.currentTimeMillis() - initialTime;
            if (memoryUsed < 1024) System.out.println("Использовано памяти: " + memoryUsed + " б");
            else if (memoryUsed < 1048576) System.out.printf("Использовано памяти: %.2f Кб\n", floatMemory/1024);
            else System.out.printf("Использовано памяти: %.2f Мб\n", floatMemory/1048576);
            if (finalTime < 1000) System.out.println("Решение найдено за " + finalTime + " миллисекунд");
            else System.out.println("Решение найдено за " + finalTime/1000 + " секунд");
            yoga.path();
        } else {
            System.out.println("Решение не найдено");
        }

    }
}

