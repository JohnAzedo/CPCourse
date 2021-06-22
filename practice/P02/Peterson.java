package P02;

public class Peterson implements Runnable {
    private static int NTHREADS = 4;
    // When flags[x] is 1, it means that x thread want to enter in the critical section
    private static int[] flags = new int[NTHREADS]; 
    private static int turn = 0;
    private static int counter = 0;
    private int id;

    public static void main(String[]args){
        // Init all flags with 0
        for(int i = 0; i<NTHREADS; i++){
            flags[i] = 1;
        }

        // Create all threads
        for(int i = 0; i<NTHREADS; i++){
            new Thread(new Peterson(i)).start();
        }
    }

    public Peterson(int id) {
        this.id = id; // Thread ID
    }  

    public void getNext(){
        counter++;
        System.out.println(this.id + " : " + counter);
    }

    // Turn logic is wrong
    @Override
    public void run(){
        for(int i = 0; i<NTHREADS; i++){
            if(i != this.id || i != turn){
                while(flags[i] == 1 && turn < NTHREADS){
                    // Wait?
                }
            }

            if(i == turn){
                getNext();
            }

            flags[i] = 0;
            turn++;                         
        
        }
    }

}
