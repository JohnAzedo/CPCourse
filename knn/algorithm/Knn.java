package algorithm;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

class Knn {
    private int k;
    private List<Double[]> data;
    private List<Double[]> tests;

    public Knn(String dataFile, String testFile) {
        this.data = loadFile(dataFile);
        this.tests = loadFile(testFile);
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


    // Read file functions
    // You can create anothre class with this functions
    // You can apply single responsibility and dependency inversion principles
    private List<Double[]> loadFile(String file){
        try {
            return getData(file);
        } catch (Exception error) {
            return new ArrayList<Double[]>();
        }
    }

    private List<Double[]> getData(String file) throws Exception {
        FileReader reader = new FileReader(file);
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<Double[]> list = new ArrayList<>();
        String[] line;

        while((line = csvReader.readNext()) != null){
            list.add(convert(line));
        }

        csvReader.close();
        reader.close();
        return list;
    }

    private Double[] convert(String[] array){
        Double[] newArray = new Double[array.length];
        for(int i=0; i<array.length; i++){
            newArray[i] = Double.parseDouble(array[i]);
        }
        return newArray;
    }
}