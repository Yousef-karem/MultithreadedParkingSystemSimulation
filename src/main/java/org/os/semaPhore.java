package org.os;
import java.util.concurrent.atomic.AtomicInteger;
public class semaPhore {
    AtomicInteger permits;
    semaPhore(int sz){
        permits=new AtomicInteger(sz);
    }
    public synchronized boolean tryAcquire()
    {
        int currentPermits = permits.get();
        if (currentPermits == 0) {
            return false;
        }
        permits.decrementAndGet(); ;
        return true;

    }
    public synchronized void release()
    {
        permits.incrementAndGet();
    }
}
