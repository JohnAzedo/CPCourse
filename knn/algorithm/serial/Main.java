package algorithm.serial;
import java.util.List;
import algorithm.Reader;

class Main {
    private static final String CSV_FILE = "/home/johnazedo/Documents/UFRN/CPCourse/knn/datasets/diabetes-300MB.csv";
    private static final String CSV_FILE_TEST = "/home/johnazedo/Documents/UFRN/CPCourse/knn/datasets/test_diabetes.csv";
    
    public static void main(String[] args) throws Exception{
        final int K = 23;
        final List<Double[]> tests = new Reader(CSV_FILE_TEST).getData();
        Knn knn = new Knn(new Reader(CSV_FILE).getData(), tests);
        System.out.println("File read!");
        knn.setNumber(K);
        System.out.println(knn.predict(tests.get(1)));
        

        // List<Double> results = knn.predictAll();
        // System.out.println(results);

        // Double accuracy = knn.accuracy(results);
        // System.out.println("N:"+ K + " -> Accuracy: " + accuracy);
    }
}