package com.ir.cars.dao.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RemoveCarCommand implements Consumer<String> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String DELETE = "DELETE " +
                                        "FROM cars_garage.cars" +
                                        "WHERE license_plate = :licensePlate";

    public RemoveCarCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(String licensePlate) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("licensePlate", licensePlate);
        jdbcTemplate.update(DELETE, parameters);
    }
}
