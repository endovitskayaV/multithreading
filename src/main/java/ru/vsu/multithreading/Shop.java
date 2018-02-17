package ru.vsu.multithreading;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    static List<Desk> getDeskList() {
        return deskList;
    }

    static void setDeskList(List<Desk> deskList1) {
        deskList.clear();
        deskList.addAll(deskList1);
    }

    static void addDesk(Desk desk) {
        deskList.add(desk);
    }

    private static List<Desk> deskList;

    static {
        deskList = new ArrayList<>();
    }

}
