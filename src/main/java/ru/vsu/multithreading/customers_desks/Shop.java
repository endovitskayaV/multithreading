package ru.vsu.multithreading.customers_desks;

import java.util.concurrent.ConcurrentSkipListSet;

public class Shop {
    private  ConcurrentSkipListSet<Desk> deskList;

    Shop(){
        deskList = new ConcurrentSkipListSet<>();
    }

    ConcurrentSkipListSet<Desk> getDeskList() {
        return deskList;
    }

    void addDesk(Desk desk) {
        deskList.add(desk);
    }


}
