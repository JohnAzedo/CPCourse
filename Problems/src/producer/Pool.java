package producer;

public interface Pool {
    Integer getItem();
    void putItem(Integer item);
}
