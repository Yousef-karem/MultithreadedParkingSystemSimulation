package org.os;

import static java.lang.System.currentTimeMillis;

public class Car extends Thread {
    ParkingLot parkingLot;
    int arrival_time, parking_time;
    String gateName;

    Car(String name, String gateName, int arrival_time, int parking_time, ParkingLot parkingLot) {
        this.setName(name);
        this.gateName = gateName;
        this.arrival_time = arrival_time;
        this.parking_time = parking_time;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            sleep(arrival_time * 1000);
            long time = currentTimeMillis();
            System.out.println(this.getName() + " from " + gateName + " arrived at time " + arrival_time);
            if (!parkingLot.tryEnter(this, time,false)) {
                System.out.println(this.getName() + " from " + gateName + " waiting for a spot.");
                while (!parkingLot.tryEnter(this, time,true)) {
                }
            }
            sleep(parking_time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        parkingLot.exit(this);
    }
}
