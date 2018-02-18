package ru.vsu.multithreading.oath_imigrants;

public class Judge implements Runnable {

    private static Judge instance;

    private Judge(){}

    public static Judge getInstance()
    {
        if (instance == null)
            instance = new Judge();
        return instance;
    }

    @Override
    public void run() {
        while (true) {
            Utils.sleepRandomUpTo(40);
            wannaCome();
            while (RegistersImmigration.immiNumInRoom < 1 || RegistersImmigration.certificatedImmiNumInRoom!=0);
            //RegistersImmigration.immiNumInRoom > RegistersImmigration.certificatedImmiNumInRoom);
            came();
            takeOath();
            gone();
        }
    }

    private void wannaCome(){
        System.out.println("Судья хочет зайти в зал");
    }

    private void came(){
        RegistersImmigration.isJudgeInRoom = true;
        System.out.println("Судья зашел в зал и начал принимать присягу. Иммигрантов зале "
                + RegistersImmigration.immiNumInRoom
                + ", жителей в зале " + RegistersImmigration.citizNumInRoom);

    }

    private void takeOath(){
        Utils.sleepRandomUpTo(30);
    }

    private void gone(){
        RegistersImmigration.isJudgeInRoom = false;
        System.out.println("Судья закончил принимать присягу и покинул зал");
    }
}
