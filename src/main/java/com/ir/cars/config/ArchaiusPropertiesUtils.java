package com.ir.cars.config;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.Property;
import org.apache.commons.lang3.Validate;

public class ArchaiusPropertiesUtils {

    public static Property<String> newStringProperty(String name, String defaultValue) {
        Validate.notNull(defaultValue, "defaultValue can't be null");
        Validate.notBlank(name, "name can't be blank");
        return DynamicPropertyFactory.getInstance().getStringProperty(name, defaultValue);
    }
}