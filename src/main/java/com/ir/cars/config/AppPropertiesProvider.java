package com.ir.cars.config;

public class AppPropertiesProvider {

    private static AppProperties instance;

    public static void initialize(AppProperties properties) {
        instance = properties;
    }

    public static AppProperties getInstance() {
        if (instance == null) {
            throw new IllegalStateException("It is necessary to initialize this store first");
        }
        return instance;
    }

}
