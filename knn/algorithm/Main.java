package algorithm;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    final double[][] data;
    final double[][] tests;

    public Main(){
        data = new Reader(Settings.CSV_FILE, Settings.NUM_INSTANCIAS, Settings.NUM_COLUMNS).getData();
        tests = new Reader(Settings.CSV_FILE_TEST, Settings.NUM_INSTANCIAS_TEST, Settings.NUM_COLUMNS).getData();
        System.out.println("File read!");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Using K = " + Settings.K);
        Main main = new Main();
        main.runSerialAlgorithm();
        // main.runMutexAlgorithm();
        // main.runAtomicAlgorithm();
    }

    public void runSerialAlgorithm(){
        Serial knn = new Serial(this.data, this.tests);
        knn.setK(Settings.K);
        knn.fit(); knn.predict();
    }

    public void runMutexAlgorithm() throws Exception{
        final ReentrantReadWriteLock mutex = new ReentrantReadWriteLock();
        Mutex knn = new Mutex(this.data, this.tests, mutex);
        knn.setConstant(Settings.K);

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

    public void runAtomicAlgorithm() throws Exception{
        Atomic knn = new Atomic(this.data, this.tests);
        knn.setConstant(Settings.K);

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
