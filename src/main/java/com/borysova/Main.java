package main.java.com.borysova;

import main.java.com.borysova.taskOne.H2Omol;

public class Main {

    public static void main(String[] args) {
        H2Omol h2o = new H2Omol();
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
