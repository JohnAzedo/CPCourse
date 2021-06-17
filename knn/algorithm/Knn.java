package algorithm;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

class Knn {
    private static final String CSV_FILE = "/home/johnazedo/Documents/UFRN/CPCourse/datasets/diabetes.csv";

    private int k;
    private List<Double> subject;
    private List<Double[]> data;

    public Knn(int k, List<Double> subject) {
        this.k = k;
        this.subject = subject;

        try {
            data = getData();

        } catch (Exception error) {
            data = new ArrayList<Double[]>();
            error.printStackTrace();
        }
    }

    // Read file 
    public List<Double[]> getData() throws Exception {
        FileReader reader = new FileReader(CSV_FILE);
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

    public Double[] convert(String[] array){
        Double[] newArray = new Double[array.length];
        for(int i=0; i<array.length; i++){
            newArray[i] = Double.parseDouble(array[i]);
        }
        return newArray;
    }

    public List<Double> distance() {
        List<Double> ed = new ArrayList<Double>();
        int dataSize = this.data.size();
        Double sum, component;
        Double[] array;
        final Double EUCLIDEAN_PARAM = 2.0;

        for(int i=0; i<dataSize; i++){
            sum = 0.0;
            array = this.data.get(i);
            for(int j=0; j<this.subject.size(); j++){
                component = array[j] - this.subject.get(j);
                sum+=Math.pow(component, EUCLIDEAN_PARAM);
            }

            ed.add(Math.pow(sum, (1/EUCLIDEAN_PARAM)));
        }
        return ed;
    }

    public int predict(){
        List<Double> distances = distance();
        List<Double> results = new ArrayList<Double>();
        final int OUTCOME_INDEX = 8;


        distances.forEach((distance) -> {
            if(distance<this.k){
                results.add(this.data.get(distances.indexOf(distance))[OUTCOME_INDEX]);
            }
        });

        
        var amountWithDiabets = Collections.frequency(results, 1.0);
        var amountWithoutDiabets = Collections.frequency(results, 0.0);


        if(amountWithDiabets>=amountWithoutDiabets){
           return 1;
        }else{
            return 0;
        }
    }
 
}