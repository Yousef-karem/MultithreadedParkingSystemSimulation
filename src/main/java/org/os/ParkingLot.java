package org.os;

import java.util.concurrent.Semaphore;

import static java.lang.System.currentTimeMillis;

public class ParkingLot {
    private Semaphore sem;
    private int numberOfCars;

    ParkingLot(int slot) {
        sem = new Semaphore(slot);
        numberOfCars = 0;
    }

    public synchronized boolean TryEnter(Car car, long time,boolean firstTime) {
        if(firstTime)
            System.out.println(car.getName() + " from " + car.gateName + " arrived at time " + car.arrival_time);
        if (sem.tryAcquire()) {
            numberOfCars++;
            long waitingTime = currentTimeMillis() - time;
            System.out.print(car.getName() + " from " + car.gateName + " parked");

            if (!firstTime) {
                System.out.print(" after waiting for " + (int) Math.ceil(waitingTime / 1000.0) + " units of time");
            }
            System.out.println(". (Parking Status: " + numberOfCars + " spots occupied)");
            return true;
        }
        if(firstTime)
            System.out.println(car.getName() + " from " + car.gateName + " waiting for a spot.");
        return false;
    }

    public synchronized void exit(Car car) {
        sem.release();
        numberOfCars--;
        System.out.println(car.getName() + " from " + car.gateName + " left after " + car.parking_time + " units of time. " + "(Parking Status: " + numberOfCars + " spots occupied)");

    }
}
