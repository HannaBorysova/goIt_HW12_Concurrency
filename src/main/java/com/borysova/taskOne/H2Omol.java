package main.java.com.borysova.taskOne;

import java.util.concurrent.Semaphore;

public class H2Omol {
    private Semaphore semaphoreH = new Semaphore(2);
    private Semaphore semaphoreO = new Semaphore(1);
    private Semaphore semaphore1 = new Semaphore(0);
    private Semaphore semaphore2 = new Semaphore(0);

    public H2Omol() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreH.acquire();
        semaphore1.release();
        semaphore2.acquire();
        releaseHydrogen.run();
        semaphoreH.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphoreO.acquire();
        semaphore2.release(2);
        semaphore1.acquire(2);
        releaseOxygen.run();
        semaphoreO.release();
    }
}
