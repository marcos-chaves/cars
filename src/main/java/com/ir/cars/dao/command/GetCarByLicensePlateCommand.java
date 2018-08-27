package com.ir.cars.dao.command;

import com.ir.cars.model.Car;
import com.ir.cars.model.ImmutableCar;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class GetCarByLicensePlateCommand implements Function<String, Car> {
    private static final String QUERY = "SELECT * " +
                                        "FROM cars_garage.cars " +
                                        "WHERE license_plate = :licensePlate";
    private NamedParameterJdbcTemplate jdbcTemplate;

    public GetCarByLicensePlateCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public Car apply(String licensePlate) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("licensePlate", licensePlate);
        try {
            return jdbcTemplate.queryForObject(QUERY, parameters,
                (rs, i) -> ImmutableCar.builder()
                    .licensePlate(rs.getString("license_plate"))
                    .fabricationYear(rs.getInt("fabrication_year"))
                    .maker(rs.getString("maker"))
                    .carModel(rs.getString("car_model"))
                    .color(rs.getString("color"))
                    .parked(rs.getBoolean("parked"))
                    .build());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
