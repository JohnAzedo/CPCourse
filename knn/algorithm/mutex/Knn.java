package algorithm.mutex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Knn implements Runnable {
    final int OUTCOME_INDEX = 8;
    final Double EUCLIDEAN_PARAM = 2.0;

    private int k;
    private List<Double[]> data;
    private Double[] test;
    private boolean result;

    private ReentrantLock mutex; 
    private static int index = 0;
    private static List<Double> outcomes;

    public Knn(List<Double[]> data, ReentrantLock mutex) {
        this.data = data;
        this.mutex = mutex;
        outcomes = new ArrayList<Double>();
    }

    public void setTest(Double[] test){
        this.test = test;
    }

    public void setNumber(int k){
        this.k = k;
    }

    public boolean getResult(){
        return result;
    }

    public void predict(){
        this.results(test);
        var amountWithDiabets = Collections.frequency(outcomes, 1.0);
        var amountWithoutDiabets = Collections.frequency(outcomes, 0.0);

        if(amountWithDiabets>=amountWithoutDiabets){
           result = true;
        }else{
            result = false;
        }
    }

    private void results(Double[] subject) {
        Double[] instance;

        while(index<this.data.size()-1){
            this.mutex.lock();
            instance = this.data.get(index);

            if(index<this.data.size()-1){
                index++;
            }
            this.mutex.unlock();

            if(getDistance(instance, subject)<this.k){
                this.mutex.lock();
                outcomes.add(instance[OUTCOME_INDEX]);
                this.mutex.unlock();
            }
        }
    }

    private Double getDistance(Double[] instance, Double[] subject){
        Double sum = 0.0;
        Double component;

        for(int j=0; j<OUTCOME_INDEX; j++){
            component = instance[j] - subject[j];
            sum+=Math.pow(component, EUCLIDEAN_PARAM);
        }
        return Math.pow(sum, (1/EUCLIDEAN_PARAM));
    }

    @Override
    public void run() {
        predict();
    }    
}