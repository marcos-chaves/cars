set schema 'cars_garage';

CREATE TABLE cars (
 license_plate VARCHAR (8) NOT NULL PRIMARY KEY,
 fabrication_year INTEGER NOT NULL,
 maker VARCHAR (50) NOT NULL,
 car_model VARCHAR (50)  NOT NULL,
 color VARCHAR(50) NOT NULL,
 parked BOOLEAN NOT NULL
);