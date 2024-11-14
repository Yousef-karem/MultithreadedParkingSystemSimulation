package org.os;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    ParkingLot parkingLot;
    List<Gate> gates;


    ParkingLotSystem(int numberOfGates, int numberOfSlots, String fileName) {
        gates = new ArrayList<Gate>();
        for (int i = 1; i <= numberOfGates; ++i) {
            Gate gate = new Gate("Gate " + i);
            gates.add(gate);
        }
        parkingLot = new ParkingLot(numberOfSlots);
        readFile(fileName);
        this.start();
    }

    private void readFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String info[] = line.split(", ");
                int gateNumber = Integer.parseInt(info[0].split(" ")[1]);
                int arrivalTime = Integer.parseInt(info[2].split(" ")[1]);
                int parkingTime = Integer.parseInt(info[3].split(" ")[1]);
                Car car = new Car(info[1], info[0], arrivalTime, parkingTime, parkingLot);
                gates.get(gateNumber - 1).addCar(car);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        for (Gate gate : gates) {
            gate.start();
        }
    }
}
