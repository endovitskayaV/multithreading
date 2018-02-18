package ru.vsu.multithreading.oath_imigrants;


import java.util.concurrent.ConcurrentSkipListSet;

public class Immigrant implements Runnable {
    private int idd;
    volatile boolean hasSweared;
    volatile boolean gotCertificate;

    Immigrant(int idd) {
        this.idd = idd;
        hasSweared = false;
        gotCertificate=false;
    }

    int getIdd() {
        return idd;
    }

    @Override
    public void run() {
        Utils.sleepRandomUpTo(30);
        wannaCome();
        while (RegistersImmigration.isJudgeInRoom ||
                (RegistersImmigration.immiNumInRoom >= RegistersImmigration.maxImiInRoom )
                || RegistersImmigration.certificatedImmiNumInRoom!=0);
            come();
            while (!RegistersImmigration.isJudgeInRoom);
            while (RegistersImmigration.isJudgeInRoom);
            this.hasSweared=true;
            findDesk(RegistersImmigration.deskList).enqueue(this);
            while (!this.gotCertificate);
            gone();

    }


    private void wannaCome() {
        System.out.println("Иммигрант " + idd + " хочет зайти в зал");
    }

    private void come(){
        RegistersImmigration.immiNumInRoom++;
        System.out.println("Иммигрант " + idd + " зашел в зал. Иммигрантов зале "
                + RegistersImmigration.immiNumInRoom
                + ", жителей в зале " + RegistersImmigration.citizNumInRoom);
    }

    private void gone(){
        RegistersImmigration.immiNumInRoom--;
        RegistersImmigration.certificatedImmiNumInRoom--;
        System.out.println("Иммигрант " + idd + " покинул зал");
    }
    private Desk findDesk(ConcurrentSkipListSet<Desk> deskList) {
        for (Desk desk : deskList) {
            if (desk.getQueue().size() == 0) return desk;
        }
        int min = Integer.MAX_VALUE;
        Desk minDesk = new Desk(0);
        for (Desk desk : deskList)
            if (desk.getQueue().size() < min) {
                minDesk = desk;
                min = desk.getQueue().size();
            }

        return minDesk;
    }

}

