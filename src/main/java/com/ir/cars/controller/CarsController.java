package com.ir.cars.controller;

import com.ir.cars.model.Car;
import com.ir.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarsController {

    @Autowired
    CarService garage;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAllCars() {
        return garage.getAllCars();
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllCars() {
        garage.removeAllCars();
    }

    @RequestMapping(value = "/{licensePlate}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable("licensePlate") String licensePlate) {
        return garage.getCarByLicensePlate(licensePlate);
    }

    @RequestMapping(value = "/{licensePlate}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCar(@PathVariable("licensePlate") String licensePlate) {
        garage.remove(licensePlate);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCar(@RequestBody Car car) {
        garage.register(car);
    }

    @RequestMapping(value = "/park/{licensePlate}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void parkCar(@PathVariable("licensePlate") String licensePlate) {
        garage.park(licensePlate);
    }

    @RequestMapping(value = "/checkout/{licensePlate}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void checkoutCar(@PathVariable("licensePlate") String licensePlate) {
        garage.checkout(licensePlate);
    }
}
