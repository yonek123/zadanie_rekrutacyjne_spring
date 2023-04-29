CREATE TABLE IF NOT EXISTS Users (
    userId INTEGER IDENTITY PRIMARY KEY,
    userName varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Facilities (
    facilityId INTEGER IDENTITY PRIMARY KEY,
    facilityName varchar(255) NOT NULL,
    facilityPrice DOUBLE NOT NULL,
    facilityArea DOUBLE NOT NULL,
    facilityDescription varchar(1000000)
);

CREATE TABLE IF NOT EXISTS Reservations (
    reservationId INTEGER IDENTITY PRIMARY KEY,
    reservationStart DATE NOT NULL,
    reservationEnd DATE NOT NULL,
    ownerId INTEGER NOT NULL FOREIGN KEY REFERENCES Users,
    lesseeId INTEGER NOT NULL FOREIGN KEY REFERENCES Users,
    reservedFacilityId INTEGER NOT NULL FOREIGN KEY REFERENCES Facilities
);