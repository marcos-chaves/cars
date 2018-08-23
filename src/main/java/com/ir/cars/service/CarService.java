package com.ir.cars.service;

import com.ir.cars.dao.CarDao;
import com.ir.cars.model.Car;

public class CarService {

    private final CarDao carDao;

    public CarService(CarDao carDao){
        this.carDao = carDao;
    }

    public void park(Car car){
        carDao.addCar(car);
    }

}
