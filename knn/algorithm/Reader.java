package algorithm;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private String file;

    public Reader(String file){
        this.file = file;
    }

    public List<Double[]> getData(){
        try {
            return loadFile();
        } catch (Exception error) {
            return new ArrayList<Double[]>();
        }
    }

    private List<Double[]> loadFile() throws Exception {
        FileReader reader = new FileReader(this.file);
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
