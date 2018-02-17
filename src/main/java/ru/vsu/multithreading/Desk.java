package ru.vsu.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

class Desk extends Thread {

    int getIdd() {
        return idd;
    }

    Queue<Customer> getQueue() {
        return queue;
    }

  private int idd;
  private Queue<Customer> queue;

    Desk(int idd){
       this.idd = idd;
       queue=new LinkedList<Customer>();
   }

   public void run(){
        serve();
   }

   void serve(){
        while(!queue.isEmpty()) {
            Customer c = queue.remove();
            System.out.println("customer " +c.getIdd() + " is serving at "+ idd + " desk...");


            try {
                Thread.sleep(5000 + new Random().nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("customer " + c.getIdd() + "payed at "+ idd + " desk...");
        }

   }

   void enque(Customer customer){
       System.out.print("customer " +customer.getIdd() + " came at "+ idd + " desk.\n" +
               queue.size()+" customers in queue: ");
       System.out.print(
               queue.stream().map(x->Integer.toString(x.getIdd())).collect(Collectors.joining(", ")));
       if (queue.size()>0) System.out.println("\nWaiting...");
   }
}
