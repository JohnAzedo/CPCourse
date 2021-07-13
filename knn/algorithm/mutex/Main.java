package algorithm.mutex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import algorithm.Reader;

public class Main {
    private static final String CSV_FILE = "/home/johnazedo/Documents/UFRN/CPCourse/knn/datasets/diabetes-300MB.csv";
    private static final String CSV_FILE_TEST = "/home/johnazedo/Documents/UFRN/CPCourse/knn/datasets/test_diabetes.csv";

    public static void main(String[] args) throws Exception {
        final int K = 23;
        final ReentrantLock mutex = new ReentrantLock();
        final Reader tests = new Reader(CSV_FILE_TEST);
        final List<Double[]> data = tests.getData();
        final int NUM_THREADS = 4;

        Knn knn = new Knn(new Reader(CSV_FILE).getData(), mutex);
        knn.setNumber(K);
        System.out.println("Files read!");

        knn.setTest(data.get(0));

        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(knn);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(knn.getResult());

    }
}
