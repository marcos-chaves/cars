package com.ir.cars.dao.command;

import com.ir.cars.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AddCarCommand implements Consumer<Car> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private String INSERT = "INSERT INTO cars " +
                            "  ( " +
                            "    fabricationYear," +
                            "    maker," +
                            "    carModel" +
                            "    parked" +
                            "  )" +
                            "  VALUES (:fabricationYear,:maker,:carModel)";

    public AddCarCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(Car car) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("fabricationYear", car.fabricationYear());
        parameters.put("maker", car.maker());
        parameters.put("carModel", car.carModel());
        parameters.put("parked", car.parked());
        jdbcTemplate.update(INSERT, parameters);
    }
}
