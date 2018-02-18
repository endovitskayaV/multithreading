package ru.vsu.multithreading.oath_imigrants;


import java.util.concurrent.ConcurrentSkipListSet;

public class Immigrant implements Runnable {
    private int idd;
    volatile boolean hasSweared;

    Immigrant(int idd) {
        this.idd = idd;
        hasSweared=false;
    }

    int getIdd() {
        return idd;
    }

    @Override
    public void run() {
        Utils.sleepRandomUpTo(30);

        System.out.println("Иммигрант " + idd + " хочет зайти в зал");
        while (RegistersImmigration.isJudgeInRoom) ;
        if (RegistersImmigration.immiNumInRoom < RegistersImmigration.maxImiInRoom) {
            RegistersImmigration.immiNumInRoom++;
            System.out.println("Иммигрант " + idd + " зашел в зал. Иммигрантов зале "
                    + RegistersImmigration.immiNumInRoom
                    + ", жителей в зале " + RegistersImmigration.citizNumInRoom);
        }
        while (!RegistersImmigration.isJudgeInRoom) ;

        findDesk(RegistersImmigration.deskList).enqueue(this);
        while (!this.hasSweared);
        RegistersImmigration.immiNumInRoom--;
        System.out.println("Иммигрант " + idd + " покинул зал");
    }

    private Desk findDesk(ConcurrentSkipListSet<Desk> deskList) {
        for (Desk desk :deskList) {
            if (desk.getQueue().size() == 0) return desk;
        }
        int min = Integer.MAX_VALUE;
        Desk minDesk=new Desk(0);
        for (Desk desk : deskList)
            if (desk.getQueue().size()<min) {
                minDesk = desk;
                min=desk.getQueue().size();
            }

        return minDesk;
    }

}

