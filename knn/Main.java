import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception{
        double[][] tests = {
            {6,162,62,0,0,24.3,0.178,50},
            {4,136,70,0,0,31.2,1.182,22},
            {1,121,78,39,74,39,0.261,28},
            {3,108,62,24,0,26,0.223,25},
            {0,181,88,44,510,43.3,0.222,26},
            {8,154,78,32,0,32.4,0.443,45},
            {1,128,88,39,110,36.5,1.057,37},
            {7,137,90,41,0,32,0.391,39},
            {0,123,72,0,0,36.3,0.258,52},
            {1,106,76,0,0,37.5,0.197,26},
            {6,190,92,0,0,35.5,0.278,66},
            {2,88,58,26,16,28.4,0.766,22},
            {9,170,74,31,0,44,0.403,43},
            {9,89,62,0,0,22.5,0.142,33},
            {10,101,76,48,180,32.9,0.171,63},
            {2,122,70,27,0,36.8,0.34,27},
            {5,121,72,23,112,26.2,0.245,30},
            {1,126,60,0,0,30.1,0.349,47},
            {1,93,70,31,0,30.4,0.315,23}
        };

        int[] outcome = {1,1,0,0,1,1,1,0,1,0,1,0,1,0,0,0,0,1,0};
        
        

        for(int number = 1; number<101; number++){
            int hits = 0;
            for(int i=0; i<tests.length; i++){
                List<Double> list = new ArrayList<Double>();
                for(double value: tests[i]){
                    list.add(Double.valueOf(value));
                }
    
                
                Knn knn = new Knn(number, list);
                int result = knn.predict();
                if(result == outcome[i]){
                    hits++;
                }
            }
    
            double accuracy = hits*100/outcome.length;
            System.out.println("N:"+ number + " -> Accuracy: " + accuracy);
        }
        
    }
}