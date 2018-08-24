package com.ir.cars.service;

import com.ir.cars.dao.CarDao;
import com.ir.cars.model.Car;

public class CarService {

    private final CarDao carDao;

    public CarService(CarDao carDao){
        this.carDao = carDao;
    }

    public void register(Car car){
        carDao.registerCar(car);
    }

    public void park(String licensePlate){
        carDao.parkCar(licensePlate);
    }

    public void checkout(String licensePlate){
        carDao.checkoutCar(licensePlate);
    }

    public void remove(String licensePlate){
        carDao.removeCar(licensePlate);
    }
}
