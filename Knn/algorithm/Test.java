package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Test extends KNN{
    private HashMap<Integer, List<Pair>> result;
    public Test(double[][] data, double[][] tests){
        super(data, tests);
    }

    public void fit(){
        int index = 0;
        double distance;
        double outcome;
        Pair pair = new Pair(0, 0);

        for(int i=0; i<this.tests.length; i++){
            while(index<this.data.length){
                outcome = this.data[index][Settings.OUTCOME_INDEX];
                distance = getDistance(this.data[index], tests[i]);

                pair.setValues(distance, outcome);
                storageDistance(i, pair);
                index++;
            }
            sortedHashMap(i);
            getNearestNeighbors(i);
        }
    }

    private void storageDistance(int index, Pair pair){
        result.get(index).add(pair);
    }

    private void sortedHashMap(int index){
        result.get(index).sort((Pair a, Pair b) -> a.getDistance().compareTo(b.getDistance()));
    }

    private void getNearestNeighbors(int index){
        List<Pair> firstElemnts = result.get(index).stream().limit(this.k).collect(Collectors.toList());
        result.replace(index, firstElemnts);    
    }

    private void createHashMap(){
        this.result = new HashMap<Integer, List<Pair>>();
    }

    
    


}
