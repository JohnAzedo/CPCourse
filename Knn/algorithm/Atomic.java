package algorithm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Atomic implements Runnable{
    private int constant;
    private double[][] tests;

    private static AtomicInteger index;
    private double[][] data;
    private static ConcurrentHashMap<Integer, List<Double>> results;

    public Atomic(double[][] data, double[][] tests){
        index = new AtomicInteger(0);
        setData(data);
        setTest(tests);
        createReusltsMap();
    }

     // Concurrent function
     public void predict(){
        int hits = 0;
        for(int key : results.keySet()){
            var outcomeTestbyId = getTestOutcome(key);
            var outcomeClassification = getClassification(results.get(key));
            if(outcomeTestbyId==outcomeClassification){
                hits++;
            }
        }
        System.out.println("Atomic: " + getAccuracy(hits, getTestSize()) + "%");
    }

    // Concurrent function
    private void algorithm() throws InterruptedException{
        double[] instance;
        while(index.get()<getDataSize()){
            if(index.get()<getDataSize()){
                index.getAndIncrement();
            }
            instance = data[index.get()];
            for(int i=0; i<getTestSize(); i++){
                if(getDistance(instance, tests[i])<this.constant){
                    putResultInMap(i, instance[Settings.OUTCOME_INDEX]);
                }
            }
        }
    }

    public void setConstant(int k){
        this.constant = k;
    }

    // Concurrent function
    public void putResultInMap(int key, double outcome) throws InterruptedException{
        // try{
        //     this.mutex.writeLock().lock();
        //     results.get(key).add(outcome);
        // } finally {
        //     this.mutex.writeLock().unlock();
        // }

        results.get(key).add(outcome);
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

    public void setData(double[][] data){
        this.data = data;
    }
    
    public void setTest(double[][] tests){
        this.tests = tests;
    }

    public int getTestSize(){
        return this.tests.length;
    }

    public int getDataSize(){
        return this.data.length-1;
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

    public void createReusltsMap(){
        results = new ConcurrentHashMap<Integer, List<Double>>();
        for(int i=0; i<getTestSize(); i++){
            results.put(i, new ArrayList<Double>());
        }
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
