package com.ir.cars.service;

import com.ir.cars.dao.CarDao;
import com.ir.cars.model.Car;

import java.util.List;

public class CarService {

    private final CarDao carDao;

    public CarService(CarDao carDao){
        this.carDao = carDao;
    }

    public List<Car> getAllCars(){return carDao.getAllCars(); }

    public void removeAllCars(){ carDao.removeAllCars(); }

    public Car getCarByLicensePlate(String licensePlate) {return carDao.getCarByLicensePlate(licensePlate); }

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
