import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception{
        List<Double> list = List.of(6.0, 162.0,62.0,0.0,0.0,24.3,0.178,50.0);
        Knn knn = new Knn(3, list);
        
    }
}