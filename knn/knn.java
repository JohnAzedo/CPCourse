class KNN {

    private int k = 3;
    private String pathDataset;

    public float euclideanDistance(float a, float b){
        float delta = Math.pow((a-b), 2);
        float result = Math.sqrt(delta);
        return delta;
    }
}