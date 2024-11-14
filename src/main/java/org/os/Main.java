package org.os;
public class Main {
    public static void main(String[] args) {
        String fileName = "input.txt";
        int numberOfGates = 3;
        int numberOfSlots = 4;
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(numberOfGates, numberOfSlots, fileName);
    }
}