package ru.vsu.multithreading;

import java.util.concurrent.ConcurrentSkipListSet;

public class Shop {
    static ConcurrentSkipListSet<Desk> getDeskList() {
        return deskList;
    }

    static void setDeskList(ConcurrentSkipListSet<Desk> deskList1) {
        deskList.clear();
        deskList.addAll(deskList1);
    }

    static void addDesk(Desk desk) {
        deskList.add(desk);
    }

    private static ConcurrentSkipListSet<Desk> deskList;

    static {
        deskList = new ConcurrentSkipListSet<>();
    }


}
