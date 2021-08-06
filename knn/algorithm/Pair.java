package algorithm;

public class Pair{
    double distance;
    double outcome;

    public Pair(double distance, double outcome){
        this.distance = distance;
        this.outcome = outcome;
    }

    public void setValues(double distance, double outcome){
        this.distance = distance;
        this.outcome = outcome;
    }

    public Double getDistance(){
        return distance;
    }

    public Double getOutcome(){
        return outcome;
    }


}
