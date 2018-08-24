package com.ir.cars.dao;

import com.ir.cars.dao.command.AddCarCommand;
import com.ir.cars.dao.command.CheckoutCarCommand;
import com.ir.cars.dao.command.ParkCarCommand;
import com.ir.cars.dao.command.RemoveCarCommand;
import com.ir.cars.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;

public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public CarDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
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
