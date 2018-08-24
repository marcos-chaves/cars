package com.ir.cars.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableCar.class)
@JsonDeserialize(as = ImmutableCar.class)
public interface Car {
    String licensePlate();

    Integer fabricationYear();

    String maker();

    String carModel();

    String color();

    Boolean parked();
}
