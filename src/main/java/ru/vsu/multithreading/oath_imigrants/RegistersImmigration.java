package ru.vsu.multithreading.oath_imigrants;


import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Semaphore;

public class RegistersImmigration {
    volatile static boolean isJudgeInRoom;
    volatile static int immiNumInRoom;
    volatile static int citizNumInRoom;
    volatile static int certificatedImmiNumInRoom;
    static final int maxImiInRoom = 5;
    static Semaphore semaphore=new Semaphore(maxImiInRoom);

    static ConcurrentSkipListSet<Desk> deskList=new ConcurrentSkipListSet<>();

}
