package com.ir.cars.dao;

import com.ir.cars.dao.command.AddCarCommand;
import com.ir.cars.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;

public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public CarDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCar(Car car) {
        new AddCarCommand(jdbcTemplate).accept(car);
    }
}
