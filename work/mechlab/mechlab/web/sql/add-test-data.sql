INSERT INTO USER (id, nimi, email, salasana, oikeustaso) VALUES (1, 'Testi Testaaja', 'test@test.test', '12345', 0);
INSERT INTO USER (id, nimi, email, salasana, oikeustaso) VALUES (999, 'Admin', 'admin@test.test', '123456', 1);

INSERT INTO MECHKOKOELMA (id, user) VALUES
    (1, (SELECT id from USER where id=1) );

INSERT INTO MECH (id, nimi, mechkokoelma, user, komponentisto, reaktori, paino) VALUES (1, "Archer", (SELECT id from MECHKOKOELMA where id=1), (SELECT id from USER where id=1), (SELECT id from KOMPONENTISTO where id=1), (SELECT id from REAKTORI where id=2), 30);

INSERT INTO KOMPONENTISTO (id, komponentti, sijainti) VALUES (1, (SELECT id from KOMPONENTISTO where id=2), 'LEFT ARM');
INSERT INTO KOMPONENTISTO (id, komponentti, sijainti) VALUES (1, (SELECT id from KOMPONENTISTO where id=2), 'RIGHT ARM');
INSERT INTO KOMPONENTISTO (id, komponentti, sijainti) VALUES (1, (SELECT id from KOMPONENTISTO where id=1), 'HEAD');

INSERT INTO REAKTORI (id, nimi, cooling, teho, massa) VALUES (1, 'Siemens 10', 10, 100, 3);
INSERT INTO REAKTORI (id, nimi, cooling, teho, massa) VALUES (2, 'Siemens 20', 10, 150, 4);
INSERT INTO REAKTORI (id, nimi, cooling, teho, massa) VALUES (3, 'Siemens 50', 15, 200, 10);

INSERT INTO KOMPONENTTI (id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo) VALUES (1, 'Small Laser', 1, 'SMALL', 1, 'ASE',  'NOT_LEGS', 3, 3, 0, 'ENERGY', 0);
INSERT INTO KOMPONENTTI (id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo) VALUES (2, 'Medium Laser', 2, 'MEDIUM', 3, 'ASE', 'NOT_LEGS', 5, 5, 0, 'ENERGY', 0);
INSERT INTO KOMPONENTTI (id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo) VALUES (3, 'Large Laser', 2, 'LARGE', 5, 'ASE', 'NOT_LEGS', 8, 9, 0, 'ENERGY', 0);

