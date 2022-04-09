package ticket;

public interface ThreadLock {
    public void request(int pid);
    public void release(int pid);
}
