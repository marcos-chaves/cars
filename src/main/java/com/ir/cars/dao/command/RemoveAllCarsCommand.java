package com.ir.cars.dao.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RemoveAllCarsCommand implements Supplier<Integer> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String DELETE = "DELETE " +
                                        "FROM cars_garage.cars";

    public RemoveAllCarsCommand(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Integer get() {
        Map<String, Object> parameters = new HashMap();
        return jdbcTemplate.update(DELETE, parameters);
    }
}
