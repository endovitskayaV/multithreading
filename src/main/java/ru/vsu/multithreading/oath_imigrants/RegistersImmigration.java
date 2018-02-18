package ru.vsu.multithreading.oath_imigrants;


import java.util.concurrent.ConcurrentSkipListSet;

public class RegistersImmigration {
    volatile static boolean isJudgeInRoom;
    volatile static int immiNumInRoom;
    volatile static int citizNumInRoom;
    volatile static int swearedImmiNumInRoom;
    static final int maxImiInRoom = 5;

    static ConcurrentSkipListSet<Desk> deskList;

}
