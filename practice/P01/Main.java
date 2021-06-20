package P01;

public class Main {  

    static int sum = 0;
    public static void main(String[]args){
        Counter counter;

        for(int i = 0; i<100; i++){
            counter = new Counter(i);
            counter.start();
        }
    }  
}
