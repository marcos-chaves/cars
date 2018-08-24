package com.ir.cars.dao;

import com.ir.cars.model.Car;

public interface CarDao {

    void registerCar(Car car);

    void parkCar(String licensePlate);

    void checkoutCar(String licensePlate);

    void removeCar(String licensePlate);

}
