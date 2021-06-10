import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception{
        Knn knn = new Knn(3, "/home/johnazedo/Documents/UFRN/CPCourse/datasets/diabetes.csv");
        // Use Arrays.toString to convert objects
        List<String[]> data = new ArrayList<String[]>();
        // Exception may be here
        data = knn.getData();
        System.out.println(Arrays.toString(data.get(5)));

    }
}