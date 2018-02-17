package ru.vsu.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Desk extends Thread {
    private int idd;
    private ConcurrentLinkedQueue<Customer> queue;
    //Phaser phaser;

    Desk(int idd){
        this.idd = idd;
        queue=new ConcurrentLinkedQueue<>();
//        this.phaser=phaser;
//        this.phaser.register();
    }

    Queue<Customer> getQueue() {
        return queue;
    }

    @Override
   public void run(){
        while (queue.isEmpty());
        serve();
   }

  private void serve(){
        while(!queue.isEmpty()) {
            Customer c = queue.peek();
            System.out.println("customer " +c.getIdd() + " is serving at "+ idd + " desk...");


            try {
               Thread.sleep(2000 + new Random().nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("customer " + c.getIdd() + " payed at "+ idd + " desk...");
            c.isServed=true;
            queue.remove();
           // notify();
           // phaser.arriveAndDeregister();
        }
        run();

   }

   synchronized void enqueue(Customer customer){
       System.out.print("customer " +customer.getIdd() + " came at "+ idd + " desk. " +
               queue.size()+" customers in queue: ");
       System.out.print(
               queue.parallelStream().map(x->Integer.toString(x.getIdd())).collect(Collectors.joining(",")));
       if (queue.size()>0) System.out.println(". Waiting...");
       else System.out.println("-. ");
       this.queue.add(customer);
   }
}
