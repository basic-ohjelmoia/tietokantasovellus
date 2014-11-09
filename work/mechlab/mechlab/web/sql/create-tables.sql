CREATE TABLE USER(
	id	INTEGER NOT NULL PRIMARY KEY,
 	nimi 	CHAR(80)	NOT NULL,
	email	CHAR(80)	NOT NULL,
	salasana CHAR(80)	NOT NULL,
	FOREIGN KEY (mechkokoelma) REFERENCES mechkokoelma(id),
	oikeustaso	INTEGER
);

CREATE TABLE MECHKOKOELMA(
	id	INTEGER NOT NULL PRIMARY KEY,
	FOREIGN KEY (USER) REFERENCES user(id)
);

CREATE TABLE MECH(
	id	INTEGER NOT NULL PRIMARY KEY,
	nimi	CHAR(40)	NOT NULL,
	FOREIGN KEY (mechkokoelma) REFERENCES mechkokoelma(id),
	FOREIGN KEY (user) REFERENCES user(id),
	FOREIGN KEY (komponentisto) REFERENCES komponentisto(id),
	FOREIGN KEY (reaktori) REFERENCES reaktori(id),
	paino	INTEGER		NOT NULL,
	panssariarvo	INTEGER	,
	CHECK (paino > 19),
	CHECK (paino < 101)
);

CREATE TABLE REAKTORI(
	id	INTEGER NOT NULL PRIMARY KEY,
	nimi	CHAR(40)	NOT NULL,
	cooling	INTEGER		NOT NULL,
	teho	INTEGER		NOT NULL,
	massa	INTEGER		NOT NULL
);

CREATE TABLE KOMPONENTISTO(
	id	INTEGER NOT NULL PRIMARY KEY,
	FOREIGN KEY (komponentti) REFERENCES komponentti(id),
	sijainti CHAR(20)	NOT NULL
);

CREATE TABLE KOMPONENTTI(
	id	INTEGER NOT NULL PRIMARY KEY,
	nimi	CHAR(40)	NOT NULL,
	massa	INTEGER		NOT NULL,
	kokoluokka	CHAR(20)	NOT NULL,
	heat	INTEGER		NOT NULL,
	kategoria	CHAR(20)	NOT NULL,
	sijoituspaikka 	CHAR(20)	NOT NULL,
	weapon_damage	INTEGER,
	weapon_maxrange	INTEGER,
	weapon_minrange	INTEGER,
	weapon_type	CHAR(20),
	weapon_ammo	INTEGER,
	varuste_type	CHAR(20),
	varuste_tier	INTEGER,
	varuste_activity CHAR(20)
);
