package ru.vsu.multithreading.oath_imigrants;

public class Main {
    private static final int immigrantsNum =10;
    private static final int citizensNum =5;
    private static final int desksNum =5;

    public static void main(String[] args) {
        for (int i=0; i<desksNum; i++) {
            Desk desk= new Desk(i + 1);
            RegistersImmigration.deskList.add(desk);
            new Thread(desk).start();
        }

        for (int i = 0; i< immigrantsNum; i++)
           new Thread(new Immigrant(i+1)).start();

        for (int i = 0; i< citizensNum; i++)
            new Thread(new Citizen(i+1)).start();

        new Thread(Judge.getInstance()).start();
  }

}
