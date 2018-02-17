package ru.vsu.multithreading;

import java.util.List;
import java.util.Random;

 class Customer extends Thread {

     int getIdd() {
         return idd;
     }

     private int idd;

   Customer(int idd){
       this.idd = idd;
    }

    @Override
    public void run() {
        come();
        chooseGoods();
        findDesk().enque(this);
        //wait
        gone();

    }

    public void come() {
        System.out.println("customer " + idd + " came. choosing goods...");
    }

    public void chooseGoods() {
        try {
            Thread.sleep(5000 + new Random().nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Desk findDesk(List<Desk> deskList) {
       for (Desk desk :deskList) {
           if (desk.getQueue().size() == 0) return desk;

       }
        int min = Integer.MAX_VALUE;
       Desk minDesk=new Desk(0);
        for (Desk desk : deskList)
        if (desk.getQueue().size()<min) minDesk = desk;

        return minDesk;
    }

    public void gone() {
        System.out.println("customer " + idd + "  is gone");
    }
}
