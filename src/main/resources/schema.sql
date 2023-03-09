CREATE TABLE Region
(
  RegionID serial integer PRIMARY KEY,
  nom character varying(30) NOT NULL,
  Habitantes integer,
  Elemento varying(20),
  Nombrearconte varying(30),
  Mundo varying(20),
  CONSTRAINT connectPersonaje PRIMARY KEY (PersonajeID)
);



CREATE TABLE Armas
(
  ArmaID serial integer PRIMARY KEY,
  TipodeArma varying(20),
  Nombre varying(30),
  Numeroestrellas integer,
  PuntosAtaque integer,
  CONSTRAINT connectPersonaje PRIMARY KEY (PersonajeID),
);

CREATE TABLE Personaje
(
  PersonajeID serial integer PRIMARY KEY,
  Nombre varying(30),
  Numeroestrellas integer,
  TipodeArma varying(20),
  Elemento varying(20),
  Sexo varying(20),
  CONSTRAINT connectRegion PRIMARY KEY (RegionID),
  CONSTRAINT connectArmas FOREIGN KEY (ArmaID)
      REFERENCES Armas (ArmaID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_Personaje_Region FOREIGN KEY (RegionID)
      REFERENCES Region (RegionID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_Personaje UNIQUE (Nombre)
);


