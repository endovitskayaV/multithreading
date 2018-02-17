package ru.vsu.multithreading;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

class Desk  implements Runnable, Comparable<Desk>{
    private int idd;
    private ConcurrentLinkedQueue<Customer> queue;

    Desk(int idd) {
        this.idd = idd;
        queue = new ConcurrentLinkedQueue<>();
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
        }
        run();

    }

    void enqueue(Customer customer) {
        String str="customer " + customer.getIdd() + " came at " + idd + " desk. "+
                queue.size() + " customers in queue";
        if (queue.size() > 0)
            str+=": "+
                    queue.parallelStream().map(x -> Integer.toString(x.getIdd())).collect(Collectors.joining(","))
                    +". Waiting...";
        System.out.println(str);
        this.queue.add(customer);
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
      if (this.idd>o.idd) return 1;
      else return -1;
    }

}
