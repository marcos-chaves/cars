package com.ir.cars.config;

import com.ir.cars.dao.CarDao;
import com.ir.cars.dao.CarDaoImpl;
import com.ir.cars.service.CarService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class CarsConfiguration {

    @Bean
    @Autowired
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        AppPropertiesProvider.initialize(new AppPropertiesArchaius());
        AppProperties carsPropertiesProvider = AppPropertiesProvider.getInstance();

        dataSource.setDriverClassName(carsPropertiesProvider.jdbcDriverClassName().getValue());
        dataSource.setJdbcUrl(carsPropertiesProvider.jdbcUrl().getValue());
        dataSource.setUsername(carsPropertiesProvider.jdbcUsername().getValue());
        dataSource.setPassword(carsPropertiesProvider.jdbcPassword().getValue());
        dataSource.setConnectionTestQuery(carsPropertiesProvider.jdbcConnectionTestQuery().getValue());

        return dataSource;
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Autowired
    public CarDao carDao(JdbcTemplate jdbcTemplate) {
        return new CarDaoImpl(jdbcTemplate);
    }

    @Bean
    @Autowired
    public CarService carService(CarDao carDao){
        return new CarService(carDao);
    }
}