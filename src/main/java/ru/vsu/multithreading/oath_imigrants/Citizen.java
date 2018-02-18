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
            wannaCome();
            while (RegistersImmigration.isJudgeInRoom);
            came();
            wathOathing();
            gone();
        }
    }

    private void wannaCome(){
        System.out.println("Житель " + idd + " хочет зайти в зал");
    }

    private void came(){
        RegistersImmigration.citizNumInRoom++;
        System.out.println("Житель " + idd + " зашел в зал. Иммигрантов зале "
                + RegistersImmigration.immiNumInRoom
                + ", жителей в зале " + RegistersImmigration.citizNumInRoom);
    }

    private void wathOathing(){
        Utils.sleepRandomUpTo(20);
    }
    private void gone(){
        RegistersImmigration.citizNumInRoom--;
        System.out.println("Житель " + idd + " покинул зал");
    }
}
