package algorithm;

import java.util.List;

class Main {
    private static final String CSV_FILE = "/home/johnazedo/Documents/UFRN/CPCourse/knn/datasets/diabetes-10MB.csv";
    private static final String CSV_FILE_TEST = "/home/johnazedo/Documents/UFRN/CPCourse/knn/datasets/test_diabetes.csv";
    
    public static void main(String[] args) throws Exception{
        Knn knn = new Knn(new Reader(CSV_FILE).getData(), new Reader(CSV_FILE_TEST).getData());

        for(int number = 1; number<101; number++){
            knn.setNumber(number);
            List<Double> results = knn.predictAll();
            Double accuracy = knn.accuracy(results);
            System.out.println("N:"+ number + " -> Accuracy: " + accuracy);
        }
        
    }
}