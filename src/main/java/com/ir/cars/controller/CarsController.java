package com.ir.cars.controller;

import com.ir.cars.model.Car;
import com.ir.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CarsController {
    @Autowired
    CarService garage;


    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello World";
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

    @RequestMapping(value = "/checkout/{licensePlate}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCar(@PathVariable("licensePlate") String licensePlate) {
        garage.remove(licensePlate);
    }
}
