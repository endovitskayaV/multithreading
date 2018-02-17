package ru.vsu.multithreading;

public class Main {
    private static final int customersNum=5;
    private static final int desksNum=2;

    public static void main(String[] args) {
        for (int i=0; i<customersNum; i++)
            new Desk(i+1).start();

        for (int i=0; i<customersNum; i++)
        new Customer(i+1).start();
  }

}
