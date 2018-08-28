package com.ir.cars.dao;

import com.ir.cars.model.Car;

import java.util.List;

public interface CarDao {
    List<Car> getAllCars();

    void removeAllCars();

    Car getCarByLicensePlate(String licensePlate);

    void registerCar(Car car);

    void parkCar(String licensePlate);

    void checkoutCar(String licensePlate);

    void removeCar(String licensePlate);

}
