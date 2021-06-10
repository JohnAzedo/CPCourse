import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Double> result = euclidianDistance();
        System.out.println(result);
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

    public List<Double> euclidianDistance() {
        List<Double> ed = new ArrayList<Double>();
        int dataSize = data.size();

        for(int i=0; i<dataSize; i++){
            double sum = 0;
            Double[] array = data.get(i);
            for(int j=0; j<subject.size(); j++){
                double test = array[j] - subject.get(j);
                sum+=Math.pow(test, 2);
            }

            ed.add(Math.sqrt(sum));
        }

        return ed;
    }
}