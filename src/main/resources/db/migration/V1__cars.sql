set schema 'cars_garage';

CREATE TABLE cars (
 fabrication_year INTEGER NOT NULL,
 maker VARCHAR (50) NOT NULL,
 car_model VARCHAR (50)  NOT NULL,
 parked BOOLEAN NOT NULL
);