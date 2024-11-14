package org.os;

import java.util.ArrayList;
import java.util.List;

public class Gate extends Thread {
    List<Car> carList;

    Gate(String name) {
        carList = new ArrayList<Car>();
        this.setName(name);
    }

    public void addCar(Car car) {
        carList.add(car);

    }

    @Override
    public void run() {
        for (Car car : carList) {
            car.start();
        }
    }

}
