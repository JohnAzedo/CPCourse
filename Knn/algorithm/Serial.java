package algorithm;

import java.util.HashMap;
import java.util.Map;

public class Serial extends KNN{

    private Map<Integer, double[]> results;

    public Serial(double[][] data, double[][] tests) {
        super(data, tests);
        createMap();
    }
    
    public void setNumber(int k){
        this.k = k;
    }

    public void predict(){
        int hits = 0, withDiabets;
        boolean classification;

        for(var entry: results.entrySet()){
            withDiabets = 0;
            classification = false;

            for(int i=0; i<entry.getValue().length; i++){
                withDiabets++;
            }

            if(withDiabets>this.k/2){
                classification = true;
            }
            
            if(classification==getTestOutcome(entry.getKey())){
                hits++;
            }

            System.out.println(classification + " - " + getTestOutcome(entry.getKey()));
        }
        System.out.println("Serial: " + getAccuracy(hits, this.tests.length) + "%");
    }

    public void fit(){
        int targetIndex = 0;
        double distance;
        while(targetIndex<this.data.length){
            for(int i=0; i<this.tests.length; i++){
                distance = getDistance(this.data[targetIndex], tests[i]);
            }
            targetIndex++;
        }
    }

    public void createMap(){
        results = new HashMap<Integer, double[]>();
        for(int i=0; i<this.tests.length; i++){
            results.put(i, new double[this.k]);
        }
    }

    public boolean getTestOutcome(int index){
        Double outcome = tests[index][Settings.OUTCOME_INDEX];
        if(outcome!=0.0){
            return true;
        }
        return false;
    }

    public static double getAccuracy(int hits, int size){
        double accuracy = (double) (hits*100/size);
        return accuracy;
    }

    

    // public double accuracy(double[] outcomes){
    //     int hits = 0;
    //     int index = 0;
        

    //     for(int i=0; i<this.tests.length; i++){
    //         int equal = -1;

    //         if(index<outcomes.length){
    //             equal = Double.compare(this.tests[i][Settings.OUTCOME_INDEX], outcomes[i]);
    //         }

    //         if(equal==0){
    //             hits++;
    //         }

    //         index++;
    //     }
    //     return Double.valueOf(hits*100/outcomes.length);
    // }

    // public double[] predictAll(){
    //     double[] outcomes = new double[this.tests.length];
        
    //     for(int i=0; i<this.tests.length; i++){
    //         outcomes[i] = predict(this.tests[i]);
    //     }

    //     return outcomes;
    // }

    // public double predict(double[] subject){
    //     List<Double> results = this.results(subject);

    //     var amountWithDiabets = Collections.frequency(results, 1.0);
    //     var amountWithoutDiabets = Collections.frequency(results, 0.0);

    //     if(amountWithDiabets>=amountWithoutDiabets){
    //        return 1.0;
    //     }else{
    //         return 0.0;
    //     }
    // }

    // private List<Double> results(double[] subject) {
    //     List<Double> outcomes = new ArrayList<Double>();
    //     double sum, component, distance;
    //     double[] instance;

    //     for(int i=0; i<this.data.length; i++){
    //         sum = 0.0;
    //         instance = this.data[i];
    //         for(int j=0; j<Settings.OUTCOME_INDEX; j++){
    //             component = instance[j] - subject[j];
    //             sum+=Math.pow(component, Settings.EUCLIDEAN_PARAM);
    //         }
    //         distance = Math.pow(sum, (1/Settings.EUCLIDEAN_PARAM));

    //         if(distance<this.k){
    //             outcomes.add(instance[Settings.OUTCOME_INDEX]);
    //         }
    //     }
    //     return outcomes;
    // }    
}