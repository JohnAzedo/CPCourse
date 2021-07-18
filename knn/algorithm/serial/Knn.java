package algorithm.serial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import algorithm.Settings;

public class Knn {
    private int k;
    private double[][] data;
    private double[][] tests;

    public Knn(double[][] data, double[][] tests) {
        this.data = data;
        this.tests = tests;
    }

    public void setNumber(int k){
        this.k = k;
    }

    public double accuracy(double[] outcomes){
        int hits = 0;
        int index = 0;
        

        for(int i=0; i<this.tests.length; i++){
            int equal = -1;

            if(index<outcomes.length){
                equal = Double.compare(this.tests[i][Settings.OUTCOME_INDEX], outcomes[i]);
            }

            if(equal==0){
                hits++;
            }

            index++;
        }

        return Double.valueOf(hits*100/outcomes.length);
    }

    public double[] predictAll(){
        double[] outcomes = new double[this.tests.length];
        
        for(int i=0; i<this.tests.length; i++){
            outcomes[i] = predict(this.tests[i]);
        }

        return outcomes;
    }

    public double predict(double[] subject){
        List<Double> results = this.results(subject);

        var amountWithDiabets = Collections.frequency(results, 1.0);
        var amountWithoutDiabets = Collections.frequency(results, 0.0);

        if(amountWithDiabets>=amountWithoutDiabets){
           return 1.0;
        }else{
            return 0.0;
        }
    }

    private List<Double> results(double[] subject) {
        List<Double> outcomes = new ArrayList<Double>();
        double sum, component, distance;
        double[] instance;

        for(int i=0; i<this.data.length; i++){
            sum = 0.0;
            instance = this.data[i];
            for(int j=0; j<Settings.OUTCOME_INDEX; j++){
                component = instance[j] - subject[j];
                sum+=Math.pow(component, Settings.EUCLIDEAN_PARAM);
            }
            distance = Math.pow(sum, (1/Settings.EUCLIDEAN_PARAM));

            if(distance<this.k){
                outcomes.add(instance[Settings.OUTCOME_INDEX]);
            }
        }
        return outcomes;
    }    
}