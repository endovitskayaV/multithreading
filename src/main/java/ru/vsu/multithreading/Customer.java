package ru.vsu.multithreading;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

class Customer extends Thread {
     private int idd;
    volatile boolean isServed;


     Customer(int idd){
         this.idd = idd;
         isServed=false;
     }

     int getIdd() {
         return idd;
     }


    @Override
    public void run() {
        come();
        chooseGoods();
        findDesk(Shop.getDeskList()).enqueue(this);
        while (!this.isServed);
        gone();

    }

    private void come() {
        System.out.println("customer " + idd + " came. choosing goods...");
    }

    private void chooseGoods() {
        try {
            Thread.sleep(5000 + new Random().nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Desk findDesk(ConcurrentSkipListSet<Desk> deskList) {
       for (Desk desk :deskList) {
           if (desk.getQueue().size() == 0) return desk;
       }
        int min = Integer.MAX_VALUE;
       Desk minDesk=new Desk(0);
        for (Desk desk : deskList)
        if (desk.getQueue().size()<min) minDesk = desk;

        return minDesk;
    }

    private void gone() {
        System.out.println("customer " + idd + "  is gone");
    }
}
