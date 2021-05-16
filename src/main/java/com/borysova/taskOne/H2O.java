package main.java.com.borysova.taskOne;

import java.util.concurrent.Semaphore;

public class H2O {
    private Semaphore semaphoreH = new Semaphore(2);
    private Semaphore semaphoreO = new Semaphore(1);
    private Semaphore semaphore1 = new Semaphore(0);
    private Semaphore semaphore2 = new Semaphore(0);

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

class H2OTest {

    public static void main(String[] args) {
        H2O h2o = new H2O();
        int n = 2;

        new Thread(() -> {
            try {
                for (int i = 0; i < 2*n; i++) {
                    h2o.hydrogen(() -> System.out.print("H"));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    h2o.oxygen(() -> System.out.print("O"));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

