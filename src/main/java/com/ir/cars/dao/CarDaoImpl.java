package com.ir.cars.dao;

import com.ir.cars.dao.command.AddCarCommand;
import com.ir.cars.dao.command.CheckoutCarCommand;
import com.ir.cars.dao.command.GetAllCarsCommand;
import com.ir.cars.dao.command.GetCarByLicensePlateCommand;
import com.ir.cars.dao.command.ParkCarCommand;
import com.ir.cars.dao.command.RemoveAllCarsCommand;
import com.ir.cars.dao.command.RemoveCarCommand;
import com.ir.cars.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public CarDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> getAllCars() {
        return new GetAllCarsCommand(jdbcTemplate).get();
    }

    @Override
    public void removeAllCars() {
        new RemoveAllCarsCommand(jdbcTemplate).get();
    }

    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        return new GetCarByLicensePlateCommand(jdbcTemplate).apply(licensePlate);
    }

    @Override
    public void registerCar(Car car) {
        new AddCarCommand(jdbcTemplate).accept(car);
    }

    @Override
    public void parkCar(String licensePlate) {
        new ParkCarCommand(jdbcTemplate).accept(licensePlate);
    }

    @Override
    public void checkoutCar(String licensePlate) {
        new CheckoutCarCommand(jdbcTemplate).accept(licensePlate);
    }

    @Override
    public void removeCar(String licensePlate) {
        new RemoveCarCommand(jdbcTemplate).accept(licensePlate);
    }
}
