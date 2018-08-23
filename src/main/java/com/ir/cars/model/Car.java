package com.ir.cars.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
//@JsonDeserialize(as = ImmutableCar.class)
//@JsonSerialize(as = ImmutableCar.class)
public interface Car {
    String year();

    String make();

    String model();

//    Optional<String> accessories = null;
}
