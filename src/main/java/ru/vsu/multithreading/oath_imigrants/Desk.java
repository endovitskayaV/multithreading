package ru.vsu.multithreading.oath_imigrants;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

class Desk implements Runnable, Comparable<Desk> {
    private int idd;
    private ConcurrentLinkedQueue<Immigrant> queue;

    Desk(int idd) {
        this.idd = idd;
        queue = new ConcurrentLinkedQueue<>();
    }

    ConcurrentLinkedQueue<Immigrant> getQueue() {
        return queue;
    }

    @Override
    public void run() {
        while (queue.isEmpty()) ;
        serve();

    }

    private void serve() {
        while (!queue.isEmpty()) {
            Immigrant immigrant = queue.peek();
            System.out.println("Иммигрант " + immigrant.getIdd() + " получает справку у  " + idd + " стойки...");
            Utils.sleepRandomUpTo(10);
            System.out.println("Иммигрант " + immigrant.getIdd() + " получил справку на " + idd + " стойке...");
            immigrant.gotCertificate = true;
            RegistersImmigration.certificatedImmiNumInRoom++;
            queue.remove();
        }
        run();

    }

    void enqueue(Immigrant immigrant) {
        String str = "Иммигрант " + immigrant.getIdd() + " подошел к " + idd + " стойке. " +
                queue.size() + " иммигрантов в очереди";
        if (queue.size() > 0)
            str += ": " +
                    queue.parallelStream().map(x -> Integer.toString(x.getIdd())).collect(Collectors.joining(","))
                    + ". Ждут...";
        System.out.println(str);
        this.queue.add(immigrant);
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
        return 31 * idd;
    }

    @Override
    public int compareTo(Desk o) {
        if (this.equals(o)) return 0;
        if (this.idd > o.idd) return 1;
        else return -1;
    }

}
