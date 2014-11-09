INSERT INTO kayttaja (kayttaja_id, nimi, email, salasana, oikeustaso) VALUES (1, 'Testi Testaaja', 'test@test.test', '12345', 0);
INSERT INTO kayttaja (kayttaja_id, nimi, email, salasana, oikeustaso) VALUES (999, 'Admin', 'admin@test.test', '123456', 1);

INSERT INTO MECHKOKOELMA (mechkokoelma_id) VALUES
    (1);




INSERT INTO REAKTORI (reaktori_id, nimi, cooling, teho, massa) VALUES (1, 'Siemens 10', 10, 100, 3);
INSERT INTO REAKTORI (reaktori_id, nimi, cooling, teho, massa) VALUES (2, 'Siemens 20', 10, 150, 4);
INSERT INTO REAKTORI (reaktori_id, nimi, cooling, teho, massa) VALUES (3, 'Siemens 50', 15, 200, 10);

INSERT INTO KOMPONENTTI (komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo) VALUES (1, 'Small Laser', 1, 'SMALL', 1, 'ASE',  'NOT_LEGS', 3, 3, 0, 'ENERGY', 0);
INSERT INTO KOMPONENTTI (komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo) VALUES (2, 'Medium Laser', 2, 'MEDIUM', 3, 'ASE', 'NOT_LEGS', 5, 5, 0, 'ENERGY', 0);
INSERT INTO KOMPONENTTI (komponentti_id, nimi, massa, kokoluokka, heat, kategoria, sijoituspaikka, weapon_damage, weapon_maxrange, weapon_minrange, weapon_type, weapon_ammo) VALUES (3, 'Large Laser', 2, 'LARGE', 5, 'ASE', 'NOT_LEGS', 8, 9, 0, 'ENERGY', 0);

INSERT INTO MECH (mech_id, nimi, collection_id, user_id, reactor_id, paino) VALUES 
   (1, 'Archer', (SELECT mechkokoelma_id from MECHKOKOELMA where mechkokoelma_id=1), (SELECT kayttaja_id from kayttaja where kayttaja_id=1), (SELECT reaktori_id from REAKTORI where reaktori_id=2), 30);

   

INSERT INTO KOMPONENTISTO (komponentisto_id, battlemech_id, component_id, sijainti) VALUES (1, (SELECT mech_id from mech where mech_id=1), (SELECT komponentti_id from KOMPONENTTI where komponentti_id=2), 'LEFT ARM');
INSERT INTO KOMPONENTISTO (komponentisto_id, battlemech_id, component_id, sijainti) VALUES (2, (SELECT mech_id from mech where mech_id=1), (SELECT komponentti_id from KOMPONENTTI where komponentti_id=2), 'RIGHT ARM');
INSERT INTO KOMPONENTISTO (komponentisto_id, battlemech_id, component_id, sijainti) VALUES (3, (SELECT mech_id from mech where mech_id=1), (SELECT komponentti_id from KOMPONENTTI where komponentti_id=1), 'HEAD');
