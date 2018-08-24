package com.ir.cars.dao.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CheckoutCarCommand implements Consumer<String> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String UPDATE = "UPDATE cars_garage.cars " +
                                        "SET parked = false " +
                                        "WHERE license_plate = :licensePlate";

    public CheckoutCarCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void accept(String licensePlate) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("licensePlate", licensePlate);
        jdbcTemplate.update(UPDATE, parameters);
    }
}
