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
            Utils.sleepRandomUpTo(20);

            System.out.println("Судья хочет зайти в зал");
            while (RegistersImmigration.immiNumInRoom < 1);
            RegistersImmigration.isJudgeInRoom = true;
            System.out.println("Судья зашел в зал и начал принимать присягу. Иммигрантов зале "
                    + RegistersImmigration.immiNumInRoom
                    + ", жителей в зале " + RegistersImmigration.citizNumInRoom);
            Utils.sleepRandomUpTo(10);
            while (RegistersImmigration.immiNumInRoom < RegistersImmigration.swearedImmiNumInRoom) ;
            RegistersImmigration.isJudgeInRoom = false;
            System.out.println("Судья закончил принимать присягу и покинул зал");
        }
    }
}
