package com.ir.cars.config;

import com.netflix.config.Property;

public interface AppProperties {
    // PostgreSQL connection properties
    Property<String> jdbcUrl();

    Property<String> jdbcDriverClassName();

    Property<String> jdbcUsername();

    Property<String> jdbcPassword();

    Property<String> jdbcConnectionTestQuery();
}
