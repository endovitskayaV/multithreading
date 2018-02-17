package ru.vsu.multithreading;

public class Main {
    private static final int customersNum=10;
    private static final int desksNum=3;

    public static void main(String[] args) {
        Shop shop=new Shop();
        for (int i=0; i<desksNum; i++) {
           Desk desk= new Desk(i + 1);
           shop.addDesk(desk);
           new Thread(desk).start();
        }

        for (int i=0; i<customersNum; i++)
            new Thread(new Customer(i+1, shop)).start();
  }

}
