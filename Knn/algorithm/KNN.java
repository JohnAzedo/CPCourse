package algorithm;

public abstract class KNN {
    protected double [][] tests;
    protected double [][] data;
    protected int k;

    public KNN(double[][] data, double[][] tests){
        this.data = data;
        this.tests = tests;
        setK(1); // Default K
    }

    public void setK(int number){
        this.k = number;
    }

    public double getDistance(double[] target, double[] neighbor){
        double sum = 0;
        for(int col=0; col<target.length; col++){
            sum += Math.pow(target[col] - neighbor[col], Settings.EUCLIDEAN_PARAM);
        }
        return Math.pow(sum, 1/Settings.EUCLIDEAN_PARAM);
    }
}