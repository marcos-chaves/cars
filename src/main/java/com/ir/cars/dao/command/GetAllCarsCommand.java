package com.ir.cars.dao.command;

import com.ir.cars.model.Car;
import com.ir.cars.model.ImmutableCar;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.function.Supplier;

public class GetAllCarsCommand implements Supplier<List<Car>> {
    private static final String QUERY = "SELECT * " +
                                        "FROM cars_garage.cars";
    private NamedParameterJdbcTemplate jdbcTemplate;

    public GetAllCarsCommand(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public List<Car> get() {
        try {
            return jdbcTemplate.query(QUERY,
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
