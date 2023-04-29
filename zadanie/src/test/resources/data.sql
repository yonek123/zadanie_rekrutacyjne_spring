INSERT INTO USERS (USERID, USERNAME) VALUES
    (0, 'januszex'),
    (1, 'nowax'),
    (2, 'bob'),
    (3, 'fred'),
    (4, 'company');

INSERT INTO FACILITIES (FACILITYID, FACILITYNAME, FACILITYPRICE, FACILITYAREA, FACILITYDESCRIPTION) VALUES
    (0, 'budynek0', 3000.00, 50.00, 'Budynek mieszkalny'),
    (1, 'budynek1', 2000.00, 40.00, 'Budynek mieszkalny'),
    (2, 'budynek2', 1500.00, 20.00, 'Budynek mieszkalny'),
    (3, 'budynek3', 5000.00, 50.00, 'Sklep'),
    (4, 'budynek4', 20000.00, 100.00, NULL);

INSERT INTO RESERVATIONS (RESERVATIONID, RESERVATIONSTART, RESERVATIONEND, OWNERID, LESSEEID, RESERVEDFACILITYID) VALUES
    (0, '2023-01-01', '2023-05-01', 0, 2, 1),
    (1, '2023-01-01', '2023-12-31', 1, 3, 2),
    (2, '2022-08-01', '2025-12-31', 4, 0, 4);