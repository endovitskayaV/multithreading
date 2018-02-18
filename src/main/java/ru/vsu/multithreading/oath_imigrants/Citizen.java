package ru.vsu.multithreading.oath_imigrants;

public class Citizen implements Runnable {
    private int idd;

    Citizen(int idd) {
        this.idd = idd;
    }

    @Override
    public void run() {
        while (true) {
            Utils.sleepRandomUpTo(30);

            System.out.println("Житель " + idd + " хочет зайти в зал");
            while (RegistersImmigration.isJudgeInRoom) ;
            RegistersImmigration.citizNumInRoom++;
            System.out.println("Житель " + idd + " зашел в зал. Иммигрантов зале "
                    + RegistersImmigration.immiNumInRoom
                    + ", жителей в зале " + RegistersImmigration.citizNumInRoom);
            Utils.sleepRandomUpTo(20);
            RegistersImmigration.citizNumInRoom--;
            System.out.println("Житель " + idd + " покинул зал");
        }
    }
}
