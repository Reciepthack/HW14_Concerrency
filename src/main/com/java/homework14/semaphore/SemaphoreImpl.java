package main.com.java.homework14.semaphore;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreImpl {
    private AtomicInteger permits;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public SemaphoreImpl(int permits) {
        this.permits = new AtomicInteger(permits);
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits.get() == 0) {
                condition.signalAll();
                condition.await();
            }
            permits.decrementAndGet();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            permits.incrementAndGet();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}



