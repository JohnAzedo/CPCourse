package algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
    private String file;
    private int sizeOfLines;
    private int sizeOfColumns;

    public Reader(String file, int sizeOfLines, int sizeOfColumns){
        this.file = file;
        this.sizeOfLines = sizeOfLines;
        this.sizeOfColumns = sizeOfColumns;
    }

    public double[][] getData(){
        try {
            return loadFile();
        } catch (Exception error) {
            return new double[this.sizeOfLines][this.sizeOfColumns];
        }
    }

    private double[][] loadFile() throws IOException {
        FileInputStream fis = new FileInputStream(this.file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        double[][] list = new double[this.sizeOfLines][this.sizeOfColumns];
        String line;
        int index = 0;

        br.readLine();

        while((line = br.readLine()) != null){
            String[] array = line.split(",");
            for(int column=0; column<array.length; column++){
                list[index][column] = Double.parseDouble(array[column]);
            }
            index++;
        }

        br.close();
        return list;
    }
}
