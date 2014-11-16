
CREATE TABLE reaktori(
	reaktori_id INTEGER NOT NULL,
	nimi	CHAR(40)	NOT NULL,
	cooling	INTEGER		NOT NULL,
	teho	INTEGER		NOT NULL,
	massa	INTEGER		NOT NULL,
	PRIMARY KEY (reaktori_id)
);


CREATE TABLE komponentti(
	komponentti_id	INTEGER NOT NULL,
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
	varuste_activity CHAR(20),
	PRIMARY KEY (komponentti_id)

);


CREATE TABLE mechkokoelma(
	mechkokoelma_id	INTEGER NOT NULL,
	PRIMARY KEY (mechkokoelma_id)
);

CREATE TABLE kayttaja(
	kayttaja_id	INTEGER NOT NULL,
 	nimi 	CHAR(80)	NOT NULL,
	email	CHAR(80)	NOT NULL,
	salasana CHAR(80)	NOT NULL,
	oikeustaso	INTEGER,
	vierailukerta INTEGER DEFAULT 1,
	collection_id INTEGER REFERENCES mechkokoelma(mechkokoelma_id) ON DELETE CASCADE,
	PRIMARY KEY (kayttaja_id)

);




CREATE TABLE mech(
	mech_id	INTEGER NOT NULL ,
	nimi	CHAR(40)	NOT NULL,
	collection_id INTEGER REFERENCES mechkokoelma(mechkokoelma_id) ON DELETE CASCADE,
	user_id INTEGER REFERENCES kayttaja(kayttaja_id) ON DELETE CASCADE,
	reactor_id INTEGER REFERENCES reaktori(reaktori_id) ON DELETE CASCADE,
	paino	INTEGER		NOT NULL,
	panssariarvo	INTEGER	,
	CHECK (paino > 19),
	CHECK (paino < 101),
	PRIMARY KEY (mech_id)
);

CREATE TABLE komponentisto(
	komponentisto_id	INTEGER NOT NULL,
	battlemech_id INTEGER REFERENCES mech(mech_id) ON DELETE CASCADE,
	component_id INTEGER REFERENCES komponentti(komponentti_id) ON DELETE CASCADE,
	sijainti CHAR(20)	NOT NULL,
	PRIMARY KEY (komponentisto_id)
	
);