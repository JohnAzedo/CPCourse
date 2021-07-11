package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Knn {
    private int k;
    private List<Double[]> data;
    private List<Double[]> tests;

    public Knn(List<Double[]> data, List<Double[]> tests) {
        this.data = data;
        this.tests = tests;
    }

    public void setNumber(int k){
        this.k = k;
    }

    public Double accuracy(List<Double> outcomes){
        final int OUTCOME_INDEX = 8;
        int hits = 0;
        int index = 0;

        for(Double[] subject: this.tests){
            int equal = -1;

            if(index<outcomes.size()){
                equal = Double.compare(subject[OUTCOME_INDEX], outcomes.get(index));
            }

            if(equal==0){
                hits++;
            }

            index++;
        }

        return Double.valueOf(hits*100/outcomes.size());
    }

    public List<Double> predictAll(){
        List<Double> outcomes = new ArrayList<Double>();

        this.tests.forEach((subject) -> {
            outcomes.add(predict(subject));
        });

        return outcomes;
    }

    public Double predict(Double[] subject){
        List<Double> results = this.results(subject);

        var amountWithDiabets = Collections.frequency(results, 1.0);
        var amountWithoutDiabets = Collections.frequency(results, 0.0);

        if(amountWithDiabets>=amountWithoutDiabets){
           return 1.0;
        }else{
            return 0.0;
        }
    }

    private List<Double> results(Double[] subject) {
        final Double EUCLIDEAN_PARAM = 2.0;
        final int OUTCOME_INDEX = 8;

        List<Double> outcomes = new ArrayList<Double>();
        Double sum, component, distance;
        Double[] instance;

        for(int i=0; i<this.data.size(); i++){
            sum = 0.0;
            instance = this.data.get(i);
            for(int j=0; j<OUTCOME_INDEX; j++){
                component = instance[j] - subject[j];
                sum+=Math.pow(component, EUCLIDEAN_PARAM);
            }
            distance = Math.pow(sum, (1/EUCLIDEAN_PARAM));

            if(distance<this.k){
                outcomes.add(instance[OUTCOME_INDEX]);
            }
        }
        return outcomes;
    }    
}