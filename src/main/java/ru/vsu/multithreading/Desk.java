package ru.vsu.multithreading;

import sun.security.krb5.internal.crypto.Des;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

class Desk extends Thread implements Comparable<Desk>{
    private int idd;
    private ConcurrentLinkedQueue<Customer> queue;
   private Semaphore semaphore;

    Desk(int idd) {
        this.idd = idd;
        queue = new ConcurrentLinkedQueue<>();
        semaphore = new Semaphore(1, true);
    }

    ConcurrentLinkedQueue<Customer> getQueue() {
        return queue;
    }

    @Override
    public void run() {
        while (queue.isEmpty()) ;

        serve();

    }

    private void serve() {
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while (!queue.isEmpty()) {
            Customer c = queue.peek();
            System.out.println("customer " + c.getIdd() + " is serving at " + idd + " desk...");


            try {
                Thread.sleep(2000 + new Random().nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("customer " + c.getIdd() + " payed at " + idd + " desk...");
            c.isServed = true;
            queue.remove();
            // notify();
            // phaser.arriveAndDeregister();
        }
       // semaphore.release();
        run();

    }

    synchronized void enqueue(Customer customer) {
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String str="customer " + customer.getIdd() + " came at " + idd + " desk. "+
                queue.size() + " customers in queue";
        if (queue.size() > 0)
            str+=": "+
                    queue.parallelStream().map(x -> Integer.toString(x.getIdd())).collect(Collectors.joining(","))
                    +". Waiting...";
        System.out.println(str);
        this.queue.add(customer);
      //  semaphore.release();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Desk desk = (Desk) obj;
        return idd == desk.idd;
    }
    @Override
    public int hashCode() {
         return  31 * idd;
    }

    @Override
    public int compareTo(Desk o) {
      if (this.equals(o)) return 0;
      if (this.idd>((Desk)o).idd) return 1;
      else return -1;
    }

}
