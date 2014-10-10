CREATE TABLE titolare (
  idtitolare SERIAL NOT NULL,
  datarilevazione DATE NOT NULL,
  codistatregione VARCHAR(45) NOT NULL,
  iva_cf VARCHAR(255) NULL,
  denominazione VARCHAR(255) NULL,
  via VARCHAR(255) NULL,
  codistat_pvcomune VARCHAR(45) NULL,
  cap VARCHAR(20) NULL,
  PRIMARY KEY(idtitolare)
);

CREATE TABLE impianto (
  idimpianto SERIAL NOT NULL,
  codimpianto INTEGER UNIQUE NOT NULL,
  annoinstallazione INTEGER  NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  PRIMARY KEY(idimpianto)
);

CREATE TABLE indirizzo (
  idindirizzo SERIAL NOT NULL,
  idimpianto INTEGER  NOT NULL,
  via VARCHAR(255) NOT NULL,
  codistat_pvcomune VARCHAR(45)  NOT NULL,
  PRIMARY KEY(idindirizzo),
  FOREIGN KEY (idimpianto) REFERENCES impianto(idimpianto)
);

CREATE TABLE localizzazione (
  idlocalizzazione SERIAL NOT NULL,
  idimpianto INTEGER  NOT NULL,
  tipo VARCHAR(45)  NULL,
  PRIMARY KEY(idlocalizzazione),
  FOREIGN KEY (idimpianto) REFERENCES impianto(idimpianto)
);

CREATE TABLE autostradale (
  idautostradale SERIAL NOT NULL,
  idindirizzo INTEGER  NOT NULL, 
  autostrada VARCHAR(255) NOT NULL,
  km INTEGER  NULL,
  area_servizio VARCHAR(255) NULL,
  direzione VARCHAR(255) NULL,
  PRIMARY KEY(idautostradale),
  FOREIGN KEY (idindirizzo) REFERENCES indirizzo(idindirizzo)
);

CREATE TABLE sospensione (
  idsospensione SERIAL NOT NULL,
  datainizio DATE NULL,
  datafine DATE NULL,
  PRIMARY KEY(idsospensione)
);

CREATE TABLE impianto_has_sospensione (
  idimpianto INTEGER  NOT NULL,
  idsospensione INTEGER  NOT NULL,
  PRIMARY KEY(idimpianto, idsospensione),
  FOREIGN KEY (idimpianto) REFERENCES impianto(idimpianto),
  FOREIGN KEY (idsospensione) REFERENCES sospensione(idsospensione)
);

CREATE TABLE autorizzazione (
  idautorizzazione SERIAL NOT NULL,
  codistatente VARCHAR(45) NULL,
  numero VARCHAR(45) NULL,
  ivatitolare VARCHAR(45) NULL,
  dataAutorizzazione DATE NULL,
  PRIMARY KEY(idautorizzazione)
);

CREATE TABLE impianto_has_autorizzazione (
  idimpianto INTEGER  NOT NULL,
  idautorizzazione INTEGER  NOT NULL,
  PRIMARY KEY(idimpianto, idautorizzazione),
  FOREIGN KEY (idimpianto) REFERENCES impianto(idimpianto),
  FOREIGN KEY (idautorizzazione) REFERENCES autorizzazione(idautorizzazione)
);

CREATE TABLE erogato (
  iderogato SERIAL NOT NULL,
  idimpianto INTEGER  NOT NULL,
  anno INT NULL,
  codistatregione VARCHAR(45) NULL,
  benzina INTEGER  NULL,
  gasolio INTEGER  NULL,
  metano INTEGER  NULL,
  gpl INTEGER  NULL,
  PRIMARY KEY(iderogato),
  FOREIGN KEY (idimpianto) REFERENCES impianto(idimpianto)
);

CREATE TABLE rilevazione (
  idrilevazione INTEGER  NOT NULL,
  codimpianto INTEGER  NOT NULL,
  datarilevazione DATE NOT NULL,
  codistatregione VARCHAR(45) NOT NULL,
  superifcie INTEGER  NULL,
  numero_addetti INTEGER  NULL,
  numero_dipendenti INTEGER  NULL,
  tipologia VARCHAR(45) NOT NULL,
  att_commericale VARCHAR(45) NOT NULL,
  autolavaggio  VARCHAR(45) NOT NULL,
  self_services  VARCHAR(45) NOT NULL,
  stato  VARCHAR(45) NOT NULL,
  bandiera VARCHAR(45) NOT NULL,
  PRIMARY KEY(idrilevazione),
  FOREIGN KEY (codimpianto) REFERENCES impianto(codimpianto)
);

CREATE TABLE km (
  idkm SERIAL NOT NULL,
  idindirizzo INTEGER  NOT NULL,
  km INTEGER  NULL,
  PRIMARY KEY(idkm),
  FOREIGN KEY (idindirizzo) REFERENCES indirizzo(idindirizzo)
);

CREATE TABLE rilevazione_has_officina (
  idrilevazione INTEGER  NOT NULL,
  officina VARCHAR(45) NOT NULL,
  PRIMARY KEY(officina, idrilevazione)
);

CREATE TABLE rilevazione_has_pagamento (
  idrilevazione INTEGER  NOT NULL,
  pagamento VARCHAR(45) NOT NULL,
  PRIMARY KEY(pagamento, idrilevazione)
);

CREATE TABLE rilevazione_has_carburante (
  id SERIAL NOT NULL,
  idrilevazione INTEGER  NOT NULL,
  carburante  VARCHAR(45) NOT NULL,
  serbatoio INTEGER  NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (idrilevazione) REFERENCES rilevazione(idrilevazione)
);

CREATE TABLE rilevazione_has_servizio (
  idrilevazione INTEGER  NOT NULL,
  servizio VARCHAR(45) NOT NULL,
  PRIMARY KEY(idrilevazione, servizio),
  FOREIGN KEY (idrilevazione) REFERENCES rilevazione(idrilevazione)
);

CREATE TABLE rilevazione_has_servizioaccessorio (
  idrilevazione INTEGER  NOT NULL,
  servizioaccessorio VARCHAR(45) NOT NULL,
  PRIMARY KEY(idrilevazione, servizioaccessorio),
  FOREIGN KEY (idrilevazione) REFERENCES rilevazione(idrilevazione)
);

--rilevazioneatt_commerciale 'sup0_30mq'
--rilevazioneatt_commerciale 'sup30_50mq'
--rilevazioneatt_commerciale 'sup150_250mq'
--rilevazioneatt_commerciale 'sup_oltre250mq'
--rilevazioneatt_commerciale 'non_rilevato'

--rilevazionestato 'attivo'
--rilevazionestato 'decaduto'
--rilevazionestato 'sospeso'
--rilevazionestato 'attesa_rilevazione'
--rilevazionestato 'non_rilevato'

--rilevazioneselfservice 'nessuno'
--rilevazioneselfservice 'prepagamento'
--rilevazioneselfservice 'postmagamento'
--rilevazioneselfservice 'non_rilevato'

--rilevazioneautolavaggio 'manuale'
--rilevazioneautolavaggio 'selfservice'
--rilevazioneautolavaggio 'carwash'
--rilevazioneautolavaggio 'non_rilevato'

--rilevazioneofficina 'autoriparazioni'
--rilevazioneofficina 'controllo_olio'
--rilevazioneofficina 'controllo_cambio_pneumatici'
--rilevazioneofficina 'non_rilevato'

--rilevazionetipologia 'generico'
--rilevazionetipologia 'non_servito'

--localizzazionetipo 'comunale'
--localizzazionetipo 'demaniale'
--localizzazionetipo 'provinciale'
--localizzazionetipo 'statale'
--localizzazionetipo 'centro_commerciale'
--localizzazionetipo 'altra_localizzazione'

--impiantotipo 'autostradale'
--impiantotipo 'stradale'
--impiantotipo 'privato'
--impiantotipo 'natante'
--impiantotipo 'motopesca'
--impiantotipo 'non_disponibile'

--pagamento 'bancomat'
--pagamento 'fidelitycard'
--pagamento 'carta_credito'
--pagamento 'non_rilevato'

--servizio 'bar'
--servizio 'giornali'
--servizio 'ristoranti'

--servizioaccessorio 'internet_point'
--servizioaccessorio 'servizi_utenti'
--servizioaccessorio 'servizio_fax'
--servizioaccessorio 'telefono_pubblico'
--servizioaccessorio 'servizi_disabili'
--servizioaccessorio 'non_rilevato'

--carburante 'benzina'
--carburante 'gasolio'
--carburante 'gpl'
--carburante 'metano'
-------------------------------------------------------------------------------Inserimenti riguardanti l'erogato-----------------------------------------------------------------------------------
INSERT INTO titolare VALUES(1, '2004-10-09', 'ab22', '1234567891234567', '', 'viale delle olimpiadi', 'BASPZ', '85100');
-------------------------------------------------------------------------------Inserimenti riguardanti un impianto--------------------------------------------------------------------------------
--Impianti
INSERT INTO impianto VALUES(1, 1, 1985, 'stradale');
--Indirizzi
INSERT INTO indirizzo VALUES(1, 1, 'viale dante 3', 'BASPZ');
--Localizzazione
INSERT INTO localizzazione VALUES(1, 1, 'provinciale');
--Autostradale
INSERT INTO km VALUES(1, 1, 50);
--Sospensione
INSERT INTO sospensione VALUES(1,'2003-1-1','2003-2-1');
--Impianto_has_sospensione
INSERT INTO impianto_has_sospensione VALUES(1,1);
--Autorizzazione
INSERT INTO autorizzazione VALUES(1, 'ab123', '4', '123456', '2000-1-1');
--Impianto_has_autorizzazione
INSERT INTO impianto_has_autorizzazione VALUES(1,1);
-------------------------------------------------------------------------------Inserimenti riguardanti un impianto--------------------------------------------------------------------------------
--Impianti
INSERT INTO impianto VALUES(2, 2, 2000, 'stradale');
--Indirizzi
INSERT INTO indirizzo VALUES(2, 2, 'viaporta nuova', 'BASPZ');
--Localizzazione
INSERT INTO localizzazione VALUES(2, 2, 'provinciale');
--Autostradale
INSERT INTO km VALUES(2, 2, 50);
--Sospensione
INSERT INTO sospensione VALUES(2,'2001-1-1','2001-2-1');
--Impianto_has_sospensione
INSERT INTO impianto_has_sospensione VALUES(2,2);
--Autorizzazione
INSERT INTO autorizzazione VALUES(2, 'ab123', '4', '123456', '2000-1-1');
--Impianto_has_autorizzazione
INSERT INTO impianto_has_autorizzazione VALUES(2,2);