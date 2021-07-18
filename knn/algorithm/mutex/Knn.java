package algorithm.mutex;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import algorithm.Settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knn implements Runnable{
    private int constant;
    private ReentrantReadWriteLock mutex;
    private double[][] tests;

    private static int index = 0 ;
    private static Map<Integer, List<Double>> results;
    private static double[][] data;

    public Knn(double[][] data, double[][] tests, ReentrantReadWriteLock mutex){
        setMutex(mutex);
        setData(data);
        setTests(tests);
        createReusltsMap();
    }

    // Concurrent function
    public void predict(){
        int hits = 0;
        for(int key : results.keySet()){
            if(getTestOutcome(key)==getClassification(results.get(key))){
                hits++;
            }
        }
        System.out.println("N: " + this.constant + "-> Accuracy: " + getAccuracy(hits, getTestSize()));
    }

    // Concurrent function
    private void algorithm() throws InterruptedException{
        double[] instance;
        while(index<getDataSize()){
            try {
                this.mutex.readLock().lock();
                if(index<getDataSize()){
                    index++;
                }
                instance = data[index];
            } finally {
                this.mutex.readLock().unlock();
            }
            
            for(int i=0; i<getTestSize(); i++){
                if(getDistance(instance, tests[i])<this.constant){
                    putResultInMap(i, instance[Settings.OUTCOME_INDEX]);
                }
            }
        }
    }

    // Concurrent function
    public void putResultInMap(int key, double outcome) throws InterruptedException{
        try{
            this.mutex.writeLock().lock();
            results.get(key).add(outcome);
        } finally {
            this.mutex.writeLock().unlock();
        }
    }

    private boolean getClassification(List<Double> outcomes){
        var amountWithDiabets = Collections.frequency(outcomes, 1.0);
        var amountWithoutDiabets = Collections.frequency(outcomes, 0.0);

        if(amountWithDiabets>=amountWithoutDiabets){
            return true;
        }else{
            return false;
        }
    }

    private double getDistance(double[] instance, double [] subject){
        double sum = 0.0;
        double component;

        for(int j=0; j<Settings.OUTCOME_INDEX; j++){
            component = instance[j] - subject[j];
            sum+=Math.pow(component, Settings.EUCLIDEAN_PARAM);
        }
        return Math.pow(sum, (1/Settings.EUCLIDEAN_PARAM));
    }

    public void setMutex(ReentrantReadWriteLock mutex){
        this.mutex = mutex;
    }

    public void setData(double[][] _data){
        data = _data;
    }

    public void setConstant(int k){
        this.constant = k;
    }

    public void setTests(double[][] tests){
        this.tests = tests;
    }

    public int getTestSize(){
        return this.tests.length;
    }

    public int getDataSize(){
        return data.length-1;
    }

    public void createReusltsMap(){
        results = new HashMap<Integer, List<Double>>();
        for(int i=0; i<getTestSize(); i++){
            results.put(i, new ArrayList<Double>());
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
    
    @Override
    public void run() {
        try {
            algorithm();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
