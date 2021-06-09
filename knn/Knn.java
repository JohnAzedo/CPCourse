import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

class Knn {
    private int k;
    private String dataset;

    public Knn(int k, String dataset){
        this.k = k;
        this.dataset = dataset;
    }
    
    // Use dataset attribute to get data
    public List<String[]> getData() throws Exception{
        FileReader reader = new FileReader(this.dataset);
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = new ArrayList<>();
        
        
        list = csvReader.readAll();
        csvReader.close();
        reader.close();
        return list;
    }
}