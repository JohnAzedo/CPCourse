package algorithm.serial;
import algorithm.Reader;
import algorithm.Settings;

class Main {
    public static void main(String[] args) throws Exception{
        final int K = 23;
        final double[][] data = new Reader(Settings.CSV_FILE, Settings.NUM_INSTANCIAS, Settings.NUM_COLUMNS).getData();
        final double[][] tests = new Reader(Settings.CSV_FILE_TEST, Settings.NUM_INSTANCIAS_TEST, Settings.NUM_COLUMNS).getData();
        Knn knn = new Knn(data, tests);
        System.out.println("File read!");
        knn.setNumber(K);
        double[] results = knn.predictAll();
        double accuracy = knn.accuracy(results);
        System.out.println("N:"+ K + " -> Accuracy: " + accuracy);
    }
}