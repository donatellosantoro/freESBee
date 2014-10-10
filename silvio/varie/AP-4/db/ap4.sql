-- CREATE DATABASE ap4;

CREATE TABLE scheda_anagrafica_professionale (
  id_scheda_anagrafica_professionale SERIAL NOT NULL,
  codice_fiscale VARCHAR(16) NULL,
  cognome VARCHAR(50) NULL,
  nome VARCHAR(50) NULL,
  sesso CHAR NULL,
  data_nascita TIMESTAMP NULL,
  codice_comune_domicilio VARCHAR(4) NULL,
  indirizzo_domicilio VARCHAR(50) NULL,
  PRIMARY KEY(id_scheda_anagrafica_professionale)
);
