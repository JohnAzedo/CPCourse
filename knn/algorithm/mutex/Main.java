package algorithm.mutex;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import algorithm.Reader;
import algorithm.Settings;

public class Main {
    public static void main(String[] args) throws Exception {
        final ReentrantReadWriteLock mutex = new ReentrantReadWriteLock();
        final double[][] data = new Reader(Settings.CSV_FILE, Settings.NUM_INSTANCIAS, Settings.NUM_COLUMNS).getData();
        final double[][] tests = new Reader(Settings.CSV_FILE_TEST, Settings.NUM_INSTANCIAS_TEST, Settings.NUM_COLUMNS).getData();
        Knn knn = new Knn(data, tests, mutex);
        knn.setConstant(Settings.K);
        System.out.println("Files read!");

        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < Settings.NUM_THREADS; i++) {
            Thread thread = new Thread(knn);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        knn.predict();
    }
}
