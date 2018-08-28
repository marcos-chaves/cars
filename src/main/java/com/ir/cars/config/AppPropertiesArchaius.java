package com.ir.cars.config;

import com.netflix.config.Property;

import java.net.URI;
import java.util.Optional;

import static com.ir.cars.config.ArchaiusPropertiesUtils.newStringProperty;


public class AppPropertiesArchaius implements AppProperties {

    private String handleDockerHost(String uri) {
        Optional<String> dockerHost =
            Optional.ofNullable(System.getenv("DOCKER_HOST")).filter(t -> !t.startsWith("unix:"));

        if (dockerHost.isPresent()) {
            String dockerIp = URI.create(dockerHost.get()).getHost();
            return uri.replaceAll("localhost|(127|0)\\.0\\.0\\.(0|1)", dockerIp);
        } else {
            return uri;
        }
    }

    @Override
    public Property<String> jdbcUrl() {
        return newStringProperty("jdbc.url", handleDockerHost("jdbc:postgresql://localhost:5432/postgres"));
    }

    @Override
    public Property<String> jdbcDriverClassName() {
        return newStringProperty("jdbc.driver.class.name", "org.postgresql.Driver");
    }

    @Override
    public Property<String> jdbcUsername() {
        return newStringProperty("username", "garage_admin");
    }

    @Override
    public Property<String> jdbcPassword() {
        return newStringProperty("password", "cars");
    }

    @Override
    public Property<String> jdbcConnectionTestQuery() {
        return newStringProperty("jdbc.connection.test.query", "SELECT 1;");
    }
//
//    @Override
//    public Property<Integer> jdbcIdleTimeout() {
//        return newIntProperty("jdbc.idle.timeout", 10);
//    }
//
//    @Override
//    public Property<Long> jdbcConnectionTimeout() {
//        return newLongProperty("jdbc.datasource.connectionTimeout", 30000L);
//    }
//
//    @Override
//    public Property<Long> jdbcMaxLifetime() {
//        return newLongProperty("jdbc.datasource.maxLifetime", 1200000L);
//    }
//
//    @Override
//    public Property<Integer> jdbcMaximumPoolSize() {
//        return newIntProperty("jdbc.pool.maximum.size", 1);
//    }
//
//    @Override
//    public Property<Integer> jdbcMinimumIdle() {
//        return newIntProperty("jdbc.pool.minimum.size", 1);
//    }

}
