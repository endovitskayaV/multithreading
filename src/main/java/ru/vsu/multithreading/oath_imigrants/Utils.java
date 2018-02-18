package ru.vsu.multithreading.oath_imigrants;

import java.util.Random;

public class Utils {
    public static void sleepRandomUpTo(int upToNum){
        try {
            Thread.sleep( new Random().nextInt(upToNum) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
