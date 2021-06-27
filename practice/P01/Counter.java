package P01;

public class Counter extends Thread{
    private int id;
    static int sum = 0;

    public Counter(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return id + ": " + sum;
    }
    
    public void printCounter(){
        System.out.println(this.toString());
    }

    public void getNext(){
        sum++;
        printCounter();
    }

    // Repeating number yet
    public void run(){
        synchronized(this){
            getNext();
        }
    }
}
